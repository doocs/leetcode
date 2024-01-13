class Solution {
    public int maximumScore(int a, int b, int c) {
        int[] s = new int[] {a, b, c};
        Arrays.sort(s);
        if (s[0] + s[1] < s[2]) {
            return s[0] + s[1];
        }
        return (a + b + c) >> 1;
    }
}