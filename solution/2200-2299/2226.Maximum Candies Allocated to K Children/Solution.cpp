class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        int left = 0, right = 1e7;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            long long cnt = 0;
            for (int& v : candies) cnt += v / mid;
            if (cnt >= k)
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }
};