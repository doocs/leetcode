class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int mx = 0;
        for (int pile : piles) {
            mx = Math.max(mx, pile);
        }
        int left = 1, right = mx;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int s = 0;
            for (int pile : piles) {
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
}