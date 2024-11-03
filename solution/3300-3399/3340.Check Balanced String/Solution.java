class Solution {
    public boolean isBalanced(String num) {
        int[] f = new int[2];
        for (int i = 0; i < num.length(); ++i) {
            f[i & 1] += num.charAt(i) - '0';
        }
        return f[0] == f[1];
    }
}
