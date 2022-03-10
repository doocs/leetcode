class Solution {
    private int[] p;

    public int removeStones(int[][] stones) {
        int n = 10010;
        p = new int[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] stone : stones) {
            p[find(stone[0])] = find(stone[1] + n);
        }
        Set<Integer> s = new HashSet<>();
        for (int[] stone : stones) {
            s.add(find(stone[0]));
        }
        return stones.length - s.size();
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}