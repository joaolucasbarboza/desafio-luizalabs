package integrated;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.exceptions.WishlistFullSizeException;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stepdefinitions.StepDefsDefault;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishlistFullSizeStep extends StepDefsDefault {

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

    private ResponseEntity<WishlistFullSizeException> response;

    private final List<ProductEntity> products = new ArrayList<>();

    @Dado("que a lista de desejos esteja cheia")
    public void givenTheWishlistIsFull(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            ProductEntity product = new ProductEntity();

            product.setId(row.get("id"));
            product.setName(row.get("name"));

            productService.add(product);
            wishlistService.add(product.getId());

            products.add(product);
        }
    }

    @Quando("eu faço uma requisição do tipo POST para adicionar um novo produto na Wishlist")
    public void whenIMakeAPOSTRequestToAddANewProductToTheWishlist() throws URISyntaxException {

        ProductEntity newProduct = new ProductEntity("66bbb8f66990481657a3e64", "Mesa gamer");

        productService.add(newProduct);

        response = testRestTemplate.postForEntity(new URI("/api/wishlist/" + newProduct.getId()), null, WishlistFullSizeException.class);
    }

    @Entao("a resposta deverá ser uma exceção de lista cheia")
    public void thenTheResponseShouldBeAWishlistFullSizeException() {
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @E("a resposta deve conter uma mensagem de erro informando que a lista de desejos está cheia")
    public void andTheResponseShouldContainAnErrorMessageIndicatingThatTheWishlistIsFull() {
        var errorResponse = response.getBody();

        assert errorResponse != null;
        assertTrue(errorResponse.getMessage().contains("Wishlist full size."));
    }
}