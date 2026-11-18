package oop;

import java.util.Optional;

public record Supplier(String name, Address address, String contactEmail) {

    public Supplier {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    public Optional<Address> findAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<String> findEmail() {
        return Optional.ofNullable(contactEmail);
    }


}
