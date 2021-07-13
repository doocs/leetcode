class Solution {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int mx = 0;
        for (auto pile : piles) {
            mx = max(mx, pile);
        }
        int left = 1, right = mx;
        while (left < right) {
            int mid = left + right >> 1;
            int s = 0;
            for (auto pile : piles) {
                s += (pile + mid - 1) / mid;
            }
            if (s <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};