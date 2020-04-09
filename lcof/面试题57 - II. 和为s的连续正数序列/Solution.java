class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int p = 1, q = 2;
        while (p < q) {
            int s = (p + q) * (q - p + 1) >> 1;
            if (s == target) {
                int[] t = new int[q - p + 1];
                for (int i = 0; i < t.length; ++i) {
                    t[i] = p + i;
                }
                list.add(t);
                ++p;
            } else if (s < target) {
                ++q;
            } else {
                ++p;
            }
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }
}