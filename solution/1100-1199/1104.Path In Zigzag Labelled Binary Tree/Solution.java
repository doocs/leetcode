class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        int n = Integer.highestOneBit(label);
        while (label > 0) {
            res.add(label);
            int pos = ((n << 1) - 1 - label) >> 1;
            label = (n >> 1) + pos;
            n >>= 1;
        }
        Collections.reverse(res);
        return res;
    }
}
