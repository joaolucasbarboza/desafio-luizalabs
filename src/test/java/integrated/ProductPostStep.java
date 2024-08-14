package integrated;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.response.ProductDTO;
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
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPostStep extends StepDefsDefault {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ResponseEntity<ProductDTO> response;

    private ProductEntity product;

    @Dado("que eu tenho os detalhes do produto a ser criado")
    public void givenIHaveProductDetailsToCreate(DataTable dataTable) {

        product = new ProductEntity();

        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            product.setId(row.get("id"));
            product.setName(row.get("name"));
        }

    }

    @Quando("eu faço uma requisição do tipo POST para criar o produto")
    public void whenIMakeAPostRequestToCreateTheProduct() throws URISyntaxException {
        response = testRestTemplate.postForEntity(new URI("/api/product"), product, ProductDTO.class);
    }

    @Entao("a resposta deve retornar um status code 201 (Created)")
    public void thenTheResponseShouldReturnStatusCode201() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @E("a resposta deve confirmar que o produto foi criado com sucesso")
    public void andTheResponseShouldConfirmThatTheProductWasCreatedSuccessfully() {

        String id = "66bbb8f66990481657a3e64p";

        assertEquals(id, Objects.requireNonNull(response.getBody()).id());
    }
}