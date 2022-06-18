class Solution {
    public long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        int[] cnt = new int[10];
        boolean neg = num < 0;
        num = Math.abs(num);
        while (num != 0) {
            cnt[(int) (num % 10)]++;
            num /= 10;
        }
        long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i) {
                while (cnt[i]-- > 0) {
                    ans = ans * 10 + i;
                }
            }
            return -ans;
        }
        if (cnt[0] > 0) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i) {
            while (cnt[i]-- > 0) {
                ans = ans * 10 + i;
            }
        }
        return ans;
    }
}