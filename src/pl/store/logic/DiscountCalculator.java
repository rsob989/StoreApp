package pl.store.logic;

import pl.store.model.product.ComputerGame;
import pl.store.model.product.Product;

import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {
    public static final String DISCOUNTED_PRODUCTS = "games";
    public static final double PRODUCT_DISCOUNT = 0.1;
    public static final double OVER_THREE_BOUGHT_PRODUCT_DISCOUNT = 0.2;

    public void discountCalculator(List<Product> products){
        brandDiscount(products);
        overThreePiecesDiscount(products);
    }

    private void brandDiscount(List<Product> products){
        products.stream().filter(x->x instanceof ComputerGame)
                .forEach(x->x.setPriceWithTax(x.getPriceWithTax()-(x.getPriceWithTax()*PRODUCT_DISCOUNT)));
    }

    private void overThreePiecesDiscount(List<Product> products){
        if(products.size()>2){
            products.stream().forEach(x->x.setPriceWithTax(x.getPriceWithTax()-
                    (x.getPriceWithTax()*OVER_THREE_BOUGHT_PRODUCT_DISCOUNT)));
        }
    }

    private void printOutProductsAfterDiscount(List<Product> products){
        products.forEach(System.out::println);
    }

    public double sumPrices(List<Product> products){
        return products.stream().mapToDouble(Product::getPriceWithTax).sum();
    }
}
