class Solution {
    private static final int MAX_WIDTH = 100;

    public int[] numberOfLines(int[] widths, String s) {
        int last = 0, row = 1;
        for (char c : s.toCharArray()) {
            int w = widths[c - 'a'];
            if (last + w <= MAX_WIDTH) {
                last += w;
            } else {
                ++row;
                last = w;
            }
        }
        return new int[]{row, last};
    }
}