class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] e : reservedSeats) {
            int i = e[0], j = 10 - e[1];
            int v = m.getOrDefault(i, 0);
            v |= 1 << j;
            m.put(i, v);
        }
        int[] masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - m.size()) << 1;
        for (int v : m.values()) {
            for (int mask : masks) {
                if ((v & mask) == 0) {
                    v |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
}