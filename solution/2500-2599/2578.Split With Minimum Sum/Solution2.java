class Solution {
    public int splitNum(int num) {
        char[] s = (num + "").toCharArray();
        Arrays.sort(s);
        int[] ans = new int[2];
        for (int i = 0; i < s.length; ++i) {
            ans[i & 1] = ans[i & 1] * 10 + s[i] - '0';
        }
        return ans[0] + ans[1];
    }
}