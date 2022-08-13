class StockSpanner {
public:
    stack<pair<int, int>> stk;

    StockSpanner() {
    }

    int next(int price) {
        int res = 1;
        while (!stk.empty() && stk.top().first <= price) {
            pair<int, int> t = stk.top();
            stk.pop();
            res += t.second;
        }
        stk.push({price, res});
        return res;
    }
};

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner* obj = new StockSpanner();
 * int param_1 = obj->next(price);
 */