class Solution {
public:
    double calculateTax(vector<vector<int>>& brackets, int income) {
        int ans = 0, prev = 0;
        for (auto& e : brackets) {
            int upper = e[0], percent = e[1];
            ans += max(0, min(income, upper) - prev) * percent;
            prev = upper;
        }
        return ans / 100.0;
    }
};