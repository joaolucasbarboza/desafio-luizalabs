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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stepdefinitions.StepDefsDefault;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishlistDeleteByIdStep extends StepDefsDefault {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;

    private ResponseEntity<WishlistDTO> response;

    private final List<ProductEntity> products = new ArrayList<>();

    @Dado("que existem produtos cadastrados na Wishlist")
    public void givenProductsAreRegisteredInTheWishlist(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            ProductEntity product = new ProductEntity();

            product.setId(row.get("id"));
            product.setName(row.get("name"));

            productService.add(product);
            wishlistService.add(product.getId());

            products.add(product);
        }
    }

    @Quando("eu faço uma requisição do tipo DELETE para remover o produto com id da Wishlist")
    public void whenIMakeADELETERequestToRemoveTheProductByIdFromTheWishlist() throws URISyntaxException {

        String productId = "66bbb8f66990481657a3e76p";

        response = testRestTemplate.exchange(
                new URI("/api/wishlist/" + productId),
                HttpMethod.DELETE,
                null,
                WishlistDTO.class
        );
    }

    @Entao("a resposta deve confirmar que o produto foi removido da Wishlist")
    public void thenTheResponseShouldConfirmThatTheProductWasRemovedFromTheWishlist() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @E("o retorno deve confirmar que a quantidade de produtos na Wishlist foi atualizada corretamente")
    public void andTheResponseShouldConfirmThatTheQuantityOfProductsInTheWishlistWasUpdatedCorrectly() {
        assertEquals(2, Objects.requireNonNull(response.getBody()).products().size());
    }
}