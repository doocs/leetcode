class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int l = 1, r = 1000000000;
        while (l < r) {
            int mid = l + r >>> 1;
            if (check(piles, H, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] piles, int h, int k) {
        int cnt = 0;
        for (int pile : piles) {
            cnt += (pile - 1) / k + 1;
        }
        return cnt <= h;
    }
}
