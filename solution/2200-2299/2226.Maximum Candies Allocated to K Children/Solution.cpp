class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        int l = 0, r = ranges::max(candies);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long long cnt = 0;
            for (int x : candies) {
                cnt += x / mid;
            }
            if (cnt >= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
