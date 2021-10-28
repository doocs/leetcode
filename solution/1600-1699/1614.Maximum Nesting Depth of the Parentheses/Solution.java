class Solution {
    public int maxDepth(String s) {
        int res = 0, depth = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                res = Math.max(res, ++depth);
            } else if (c == ')') {
                --depth;
            }
        }
        return res;
    }
}