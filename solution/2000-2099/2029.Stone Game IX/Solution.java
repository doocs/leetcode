class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] c = new int[3];
        for (int s : stones) {
            ++c[s % 3];
        }
        int[] t = new int[]{c[0], c[2], c[1]};
        return check(c) || check(t);
    }

    private boolean check(int[] c) {
        if (c[1] == 0) {
            return false;
        }
        --c[1];
        int turn = 1 + Math.min(c[1], c[2]) * 2 + c[0];
        if (c[1] > c[2]) {
            --c[1];
            ++turn;
        }
        return turn % 2 == 1 && c[1] != c[2];
    }
}