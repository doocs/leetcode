class Cashier {
public:
    int i;
    int n;
    int discount;
    unordered_map<int, int> d;

    Cashier(int n, int discount, vector<int>& products, vector<int>& prices) {
        this->i = 0;
        this->n = n;
        this->discount = discount;
        for (int j = 0; j < products.size(); j++) {
            d[products[j]] = prices[j];
        }
    }

    double getBill(vector<int> product, vector<int> amount) {
        i = (i + 1) % n;
        double x = 0;
        for (int j = 0; j < product.size(); j++) {
            x += d[product[j]] * amount[j];
        }
        if (i == 0) {
            return x - (discount * x) / 100.0;
        }
        return x;
    }
};

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier* obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj->getBill(product,amount);
 */