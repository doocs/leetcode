public class Solution {
    public bool IsBalanced(string num) {
        int[] f = new int[2];
        for (int i = 0; i < num.Length; ++i) {
            f[i & 1] += num[i] - '0';
        }
        return f[0] == f[1];
    }
}
