class Solution {
public:
    int minPenalty(int period, vector<int>& lights, vector<int>& arrivalTime) {
        int mx = ranges::max(lights);

        int ans = 0;

        for (int x : arrivalTime) {
            int r = x % period;

            if (r >= mx) {
                ans = max(ans, period - r);
            }
        }

        return ans;
    }
};