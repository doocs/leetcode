class StockPrice {
public:
    int lastTs;
    unordered_map<int, int> mp;
    map<int, int> counter;

    StockPrice() {
    }

    void update(int timestamp, int price) {
        if (mp.count(timestamp)) {
            int oldPrice = mp[timestamp];
            --counter[oldPrice];
            if (counter[oldPrice] == 0) counter.erase(oldPrice);
        }
        mp[timestamp] = price;
        ++counter[price];
        lastTs = max(lastTs, timestamp);
    }

    int current() {
        return mp[lastTs];
    }

    int maximum() {
        return counter.rbegin()->first;
    }

    int minimum() {
        return counter.begin()->first;
    }
};

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice* obj = new StockPrice();
 * obj->update(timestamp,price);
 * int param_2 = obj->current();
 * int param_3 = obj->maximum();
 * int param_4 = obj->minimum();
 */