public class Solution {
    public int MaximumTastiness(int[] price, int k) {
        Array.Sort(price);
        int l = 0, r = price[price.Length - 1] - price[0];
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(price, mid, k)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private bool check(int[] price, int x, int k) {
        int cnt = 0, pre = -x;
        foreach (int cur in price) {
            if (cur - pre >= x) {
                ++cnt;
                pre = cur;
            }
        }
        return cnt >= k;
    }
}
