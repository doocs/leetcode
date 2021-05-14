class ProductOfNumbers {
    private List<Integer> preProduct;

    public ProductOfNumbers() {
        preProduct = new ArrayList<>();
    }
    
    public void add(int num) {
        if (num == 0) {
            preProduct.clear();
            return;
        }
        if (preProduct.isEmpty()) {
            preProduct.add(1);
        }
        preProduct.add(num * preProduct.get(preProduct.size() - 1));
    }
    
    public int getProduct(int k) {
        return preProduct.size() <= k ? 0 : preProduct.get(preProduct.size() - 1) / preProduct.get(preProduct.size() - 1 - k); 
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */