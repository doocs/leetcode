class StockPrice {
public:
    StockPrice() {
    }

    void update(int timestamp, int price) {
        if (d.count(timestamp)) {
            ls.erase(ls.find(d[timestamp]));
        }
        d[timestamp] = price;
        ls.insert(price);
        last = max(last, timestamp);
    }

    int current() {
        return d[last];
    }

    int maximum() {
        return *ls.rbegin();
    }

    int minimum() {
        return *ls.begin();
    }

private:
    unordered_map<int, int> d;
    multiset<int> ls;
    int last = 0;
};

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice* obj = new StockPrice();
 * obj->update(timestamp,price);
 * int param_2 = obj->current();
 * int param_3 = obj->maximum();
 * int param_4 = obj->minimum();
 */