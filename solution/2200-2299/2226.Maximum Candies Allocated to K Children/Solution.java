class Solution {
    public int maximumCandies(int[] candies, long k) {
        int l = 0, r = Arrays.stream(candies).max().getAsInt();
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long cnt = 0;
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
}
