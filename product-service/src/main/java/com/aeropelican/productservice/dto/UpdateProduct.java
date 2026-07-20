import lombok.Data;
@Data
public class UpdateProduct {
    private Integer productId;
    private String productName;
    private String category;
    private double price;
    private Integer quantity;
}
