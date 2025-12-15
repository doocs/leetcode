class Solution {
public:
    int bestClosingTime(string customers) {
        int ans = 0;
        int cost = 0;
        for (char c : customers) {
            if (c == 'Y') {
                cost++;
            }
        }
        int mn = cost;
        for (int j = 1; j <= customers.size(); ++j) {
            cost += customers[j - 1] == 'N' ? 1 : -1;
            if (cost < mn) {
                ans = j;
                mn = cost;
            }
        }
        return ans;
    }
};
