class ProductOfNumbers {
    private List<Integer> s = new ArrayList<>();

    public ProductOfNumbers() {
        s.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            s.clear();
            s.add(1);
            return;
        }
        s.add(s.get(s.size() - 1) * num);
    }

    public int getProduct(int k) {
        int n = s.size();
        return n <= k ? 0 : s.get(n - 1) / s.get(n - k - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */