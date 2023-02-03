class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, j = 0;
        Set<Character> vis = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            while (vis.contains(s.charAt(i))) {
                vis.remove(s.charAt(j++));
            }
            vis.add(s.charAt(i));
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}