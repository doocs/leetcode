class Solution {
public:
    int lateFee(vector<int>& daysLate) {
        auto f = [](int x) {
            if (x == 1) {
                return 1;
            } else if (x > 5) {
                return 3 * x;
            } else {
                return 2 * x;
            }
        };

        int ans = 0;
        for (int x : daysLate) {
            ans += f(x);
        }
        return ans;
    }
};
