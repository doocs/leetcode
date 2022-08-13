class Solution {
public:
    double calculateTax(vector<vector<int>>& brackets, int income) {
        double ans = 0;
        int idx = 0, prev = 0;
        while (income) {
            int a = brackets[idx][0], b = brackets[idx][1];
            int d = a - prev;
            ans += min(d, income) * b / 100.0;
            if (income <= d) break;
            income -= d;
            ++idx;
            prev = a;
        }
        return ans;
    }
};