class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int x = 1, i = 1;
        while ((x << 1) <= label) {
            x <<= 1;
            ++i;
        }
        List<Integer> ans = new ArrayList<>();
        for (; i > 0; --i) {
            ans.add(label);
            label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1;
        }
        Collections.reverse(ans);
        return ans;
    }
}