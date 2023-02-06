class Cashier {
public:
    Cashier(int n, int discount, vector<int>& products, vector<int>& prices) {
        this->n = n;
        this->discount = discount;
        for (int j = 0; j < products.size(); ++j) {
            d[products[j]] = prices[j];
        }
    }

    double getBill(vector<int> product, vector<int> amount) {
        int dis = (++i) % n == 0 ? discount : 0;
        double ans = 0;
        for (int j = 0; j < product.size(); ++j) {
            int x = d[product[j]] * amount[j];
            ans += x - (dis * x) / 100.0;
        }
        return ans;
    }

private:
    int i = 0;
    int n;
    int discount;
    unordered_map<int, int> d;
};

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier* obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj->getBill(product,amount);
 */