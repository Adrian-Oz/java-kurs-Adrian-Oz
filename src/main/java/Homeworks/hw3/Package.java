package Homeworks.hw3;

public class Package {

    final int[] PL_FEE = {1200, 150};
    final int[] EU_FEE = {2500, 400};
    final int[] WORLD_FEE = {4900, 900};



    private String code;
    private int weight;
    private String local;


    public int getFee() {
        int startKg = (int) Math.ceil((this.weight / 1000d));
        return switch (this.local) {
            case "PL" -> {
                yield PL_FEE[0] + startKg * PL_FEE[1];
            }
            case "EU" -> {
                yield EU_FEE[0] + startKg * EU_FEE[1];
            }
            case "WORLD" -> {
                yield WORLD_FEE[0] + startKg * WORLD_FEE[1];
            }
            default ->
                    -1; // Should throw an exception here, for now defaults to -1 to indicate that something went wrong
        };


    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Package{" +
                "local='" + local + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                '}';
    }
}
