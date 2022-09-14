class Solution {
    public int flipLights(int n, int presses) {
        int[] ops = new int[] {0b111111, 0b010101, 0b101010, 0b100100};
        Set<Integer> vis = new HashSet<>();
        n = Math.min(n, 6);
        for (int mask = 0; mask < 1 << 4; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (cnt <= presses && cnt % 2 == presses % 2) {
                int t = 0;
                for (int i = 0; i < 4; ++i) {
                    if (((mask >> i) & 1) == 1) {
                        t ^= ops[i];
                    }
                }
                t &= ((1 << 6) - 1);
                t >>= (6 - n);
                vis.add(t);
            }
        }
        return vis.size();
    }
}