class Solution {
    public long numberOfSubstrings(String s) {
        int[] counter = new int[26];
        long ans = 0;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            ++counter[i];
            ans += counter[i];
        }
        return ans;
    }
}