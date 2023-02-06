class ProductOfNumbers {
public:
    ProductOfNumbers() {
        s.push_back(1);
    }

    void add(int num) {
        if (num == 0) {
            s.clear();
            s.push_back(1);
            return;
        }
        s.push_back(s.back() * num);
    }

    int getProduct(int k) {
        int n = s.size();
        return n <= k ? 0 : s.back() / s[n - k - 1];
    }

private:
    vector<int> s;
};

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers* obj = new ProductOfNumbers();
 * obj->add(num);
 * int param_2 = obj->getProduct(k);
 */