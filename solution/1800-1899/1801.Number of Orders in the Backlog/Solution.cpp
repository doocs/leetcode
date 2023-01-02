class Solution {
public:
    int getNumberOfBacklogOrders(vector<vector<int>>& orders) {
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> sell;
        priority_queue<pii> buy;
        for (auto& e : orders) {
            int p = e[0], a = e[1], t = e[2];
            if (t == 0) {
                while (a && !sell.empty() && sell.top().first <= p) {
                    auto [x, y] = sell.top();
                    sell.pop();
                    if (a >= y) {
                        a -= y;
                    } else {
                        sell.push({x, y - a});
                        a = 0;
                    }
                }
                if (a) {
                    buy.push({p, a});
                }
            } else {
                while (a && !buy.empty() && buy.top().first >= p) {
                    auto [x, y] = buy.top();
                    buy.pop();
                    if (a >= y) {
                        a -= y;
                    } else {
                        buy.push({x, y - a});
                        a = 0;
                    }
                }
                if (a) {
                    sell.push({p, a});
                }
            }
        }
        long ans = 0;
        while (!buy.empty()) {
            ans += buy.top().second;
            buy.pop();
        }
        while (!sell.empty()) {
            ans += sell.top().second;
            sell.pop();
        }
        const int mod = 1e9 + 7;
        return ans % mod;
    }
};