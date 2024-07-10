class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] c1 = new int[3];
        for (int x : stones) {
            c1[x % 3]++;
        }
        int[] c2 = {c1[0], c1[2], c1[1]};
        return check(c1) || check(c2);
    }

    private boolean check(int[] cnt) {
        if (--cnt[1] < 0) {
            return false;
        }
        int r = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) {
            --cnt[1];
            ++r;
        }
        return r % 2 == 1 && cnt[1] != cnt[2];
    }
}