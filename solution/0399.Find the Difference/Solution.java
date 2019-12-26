class Solution {
    public char findTheDifference(String s, String t) {
        char[] as = s.toCharArray();
        char[] at = t.toCharArray();

        int res = 0;
        for (char a : as) {
            res ^= a;
        }
        for (char a : at) {
            res ^= a;
        }
        return (char)res;
    }
}