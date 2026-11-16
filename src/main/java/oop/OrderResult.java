package oop;

import java.util.List;

public record OrderResult(List<Product> accepted, List<String> denied) {
}
