package integrated;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.response.WishlistDTO;
import com.luizalabs.wishlist.service.ProductService;
import com.luizalabs.wishlist.service.WishlistService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stepdefinitions.StepDefsDefault;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistGetByIdStep extends StepDefsDefault {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;

    private ResponseEntity<WishlistDTO> response;

    private final List<ProductEntity> products = new ArrayList<>();

    @Dado("que a lista de desejos contém produtos previamente cadastrados")
    public void givenTheWishlistContainsPreviouslyRegisteredProducts(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            ProductEntity product = new ProductEntity();
            product.setId(row.get("id"));
            product.setName(row.get("name"));

            productService.add(product);
            wishlistService.add(product.getId());

            products.add(product);
        }
    }

    @Quando("eu faço uma requisição do tipo GET para verificar se o produto está na Wishlist")
    public void whenIMakeAGETRequestToCheckIfTheProductIsInTheWishlist() throws URISyntaxException {
        String productId = "66bbb8f66990481657a3e532";
        response = testRestTemplate.getForEntity(new URI("/api/wishlist/" + productId), WishlistDTO.class);
    }

    @Quando("eu faço uma requisição do tipo GET para verificar se o produto não está na Wishlist")
    public void whenIMakeAGETRequestToCheckIfTheProductIsNotInTheWishlist() throws URISyntaxException {
        String productId = "66bbb8f66990481657a3e534";
        response = testRestTemplate.getForEntity(new URI("/api/wishlist/" + productId), WishlistDTO.class);
    }

    @Entao("a resposta deve confirmar que o produto foi encontrado na Wishlist")
    public void thenTheResponseShouldConfirmThatTheProductWasFoundInTheWishlist() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Entao("a resposta deve retornar uma exceção de produto não encontrado na Wishlist")
    public void thenTheResponseShouldReturnAProductNotFoundException() {
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @E("a resposta deve confirmar que o produto está na Wishlist")
    public void andTheResponseShouldConfirmThatTheProductIsInTheWishlist() {
        assertEquals(1, Objects.requireNonNull(response.getBody()).products().size());
    }

    @E("a resposta deve indicar que o produto não está na Wishlist")
    public void andTheResponseShouldIndicateThatTheProductIsNotInTheWishlist() {
        assertEquals(0, Objects.requireNonNull(response.getBody()).products().size());
    }
}