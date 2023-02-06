class Cashier {
    private int i;
    private int n;
    private int discount;
    private Map<Integer, Integer> d = new HashMap<>();

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        for (int j = 0; j < products.length; ++j) {
            d.put(products[j], prices[j]);
        }
    }

    public double getBill(int[] product, int[] amount) {
        int dis = (++i) % n == 0 ? discount : 0;
        double ans = 0;
        for (int j = 0; j < product.length; ++j) {
            int p = product[j], a = amount[j];
            int x = d.get(p) * a;
            ans += x - (dis * x) / 100.0;
        }
        return ans;
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */