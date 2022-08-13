class Solution {
public:
    int shoppingOffers(vector<int>& price, vector<vector<int>>& special, vector<int>& needs) {
        int ans = total(price, needs);
        vector<int> t;
        for (auto& offer : special) {
            t.clear();
            for (int j = 0; j < needs.size(); ++j) {
                if (offer[j] > needs[j]) {
                    t.clear();
                    break;
                }
                t.push_back(needs[j] - offer[j]);
            }
            if (!t.empty()) ans = min(ans, offer[offer.size() - 1] + shoppingOffers(price, special, t));
        }
        return ans;
    }

    int total(vector<int>& price, vector<int>& needs) {
        int s = 0;
        for (int i = 0; i < price.size(); ++i) s += price[i] * needs[i];
        return s;
    }
};