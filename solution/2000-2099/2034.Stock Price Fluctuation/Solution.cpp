class StockPrice {
private:
    int lastTs;
    priority_queue<int> mx;
    priority_queue<int, vector<int>, greater<int>> mi;
    unordered_map<int, int> mp;
    unordered_map<int, int> counter;
public:
    StockPrice() {
        
    }
    
    void update(int timestamp, int price) {
        if (mp.find(timestamp) != mp.end())
        {
            int oldPrice = mp[timestamp];
            --counter[oldPrice];
        }
        mp[timestamp] = price;
        lastTs = max(lastTs, timestamp);
        ++counter[price];
        mi.push(price);
        mx.push(price);
    }
    
    int current() {
        return mp[lastTs];
    }
    
    int maximum() {
        while (!counter[mx.top()]) mx.pop();
        return mx.top();
    }
    
    int minimum() {
        while (!counter[mi.top()]) mi.pop();
        return mi.top();
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