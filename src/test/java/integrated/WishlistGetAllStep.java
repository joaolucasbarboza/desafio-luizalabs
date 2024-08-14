package integrated;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.response.WishlistDTO;
import com.luizalabs.wishlist.service.ProductService;
import com.luizalabs.wishlist.service.WishlistService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import stepdefinitions.StepDefsDefault;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WishlistGetAllStep extends StepDefsDefault {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @After
    public void tearDown() {
        wishlistRepository.deleteAll();
        productRepository.deleteAll();
    }

    private ResponseEntity<WishlistDTO> response;

    private final List<ProductEntity> products = new ArrayList<>();

    @Dado("que existem produtos cadastrados na lista de desejos")
    public void queExistemProdutosCadastradosNaListaDeDesejos(DataTable dataTable) {

        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            ProductEntity product = new ProductEntity();

            product.setId(row.get("id"));
            product.setName(row.get("name"));

            productService.add(product);
            wishlistService.add(product.getId());

            products.add(product);
        }
    }

    @Quando("eu faço uma requisição do tipo GET para obter a lista de desejos")
    public void euFacoUmaRequisicaoDoTipoGETParaObterAListaDeDesejos() throws URISyntaxException {
        response = testRestTemplate.getForEntity(new URI("/api/wishlist"), WishlistDTO.class);
    }

    @Entao("a resposta deve ser um status code {int}")
    public void aRespostaDeveSerUmStatusCode(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @E("a resposta deve retornar a lista de desejos com os produtos cadastrados anteriormente")
    public void aRespostaDeveConterOsProdutosDentroDaListaDeDesejosQueForamCadastradosPreviamente() {
        WishlistDTO wishlistResponse = response.getBody();

        assert Objects.requireNonNull(wishlistResponse).products() != null : "A lista de produtos é nula";
        assertFalse(wishlistResponse.products().isEmpty());
    }
}
