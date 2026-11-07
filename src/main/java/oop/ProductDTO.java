package oop;

record ProductDTO(String sku, String name, double netPrice, Category category) {

    ProductDTO {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("Product number is required");
        }
        if (name == null || name.isBlank() || !name.equals(name.strip())) {
            throw new IllegalArgumentException(
                    "Name must not be empty or contain whitespace at the beginning or end."
            );
        }
        if (netPrice <= 0) {
            throw new IllegalArgumentException("Price has to be a positive number");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category is required");
        }
        sku = sku.strip().toUpperCase();


    }

    public double grossPrice() {
        return category.calculateGross(netPrice);
    }

    public static ProductDTO from(Product p) {
        return new ProductDTO(p.getSku(), p.getName(), p.getNetPrice(), p.getCategory());
    }

    static ProductDTO fromCsvLine(String line) {
        String[] csvParts = line.split(";");
        if (csvParts.length != 4) {
            throw new IllegalArgumentException("Broken csv");
        }
//      Added for clarity
        String sku = csvParts[0];
        String name = csvParts[1];

        double netPrice = Double.parseDouble(csvParts[2]);
        Category category = Category.valueOf(csvParts[3]);

        return new ProductDTO(sku, name, netPrice, category);

    }
}
