class Solution {
public:
    vector<int> corpFlightBookings(vector<vector<int>>& bookings, int n) {
        vector<int> ans(n);
        for (auto& e : bookings) {
            int first = e[0], last = e[1], seats = e[2];
            ans[first - 1] += seats;
            if (last < n) {
                ans[last] -= seats;
            }
        }
        for (int i = 1; i < n; ++i) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }
};