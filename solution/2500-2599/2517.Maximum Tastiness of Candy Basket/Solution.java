class Solution {
    private int[] price;
    private int k;

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        this.price = price;
        this.k = k;
        int left = 0, right = 1000000000;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int x) {
        int s = price[0];
        int cnt = 1;
        for (int i = 1; i < price.length; ++i) {
            if (price[i] - s >= x) {
                s = price[i];
                ++cnt;
            }
        }
        return cnt >= k;
    }
}