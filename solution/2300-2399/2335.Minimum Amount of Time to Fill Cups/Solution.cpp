class Solution {
public:
    int fillCups(vector<int>& amount) {
        int ans = 0;
        while (amount[0] + amount[1] + amount[2]) {
            sort(amount.begin(), amount.end());
            ++ans;
            amount[2]--;
            amount[1] = max(0, amount[1] - 1);
        }
        return ans;
    }
};