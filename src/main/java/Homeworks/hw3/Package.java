package Homeworks.hw3;

public class Package {

    final int[] PL_FEE = {1200, 150};
    final int[] EU_FEE = {2500, 400};
    final int[] WORLD_FEE = {4900, 900};

//    public Package(String code, int weight, String local) {
//        this.code = code;
//        this.weight = weight;
//        this.local = local;
//
//    }

//    public Package() {
//    }

    private String code;
    private int weight;
    private String local;
    private  int fee;

    public int getFee() {
        int fee = 0;

        int startKg = (int) Math.ceil((this.weight / 1000d));

        switch (this.local) {
            case "PL" -> {
                fee = PL_FEE[0] + startKg * PL_FEE[1];
            }
            case "EU" -> {
                fee = EU_FEE[0] + startKg * EU_FEE[1];
            }
            case "WORLD" -> {
                fee = WORLD_FEE[0] + startKg * WORLD_FEE[1];
            }
        }

        this.fee = fee;
        return fee;

    }
    public String feeToString() {
        int zl = this.fee / 100;
        int gr = this.fee % 100;
        return String.format("%d,%02d zł", zl, gr);
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
