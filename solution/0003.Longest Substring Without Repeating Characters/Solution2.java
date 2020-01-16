class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, length = s.length(), max = 0;
        Set<Character> set = new HashSet<>();
        while (i < length && j < length) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i++));
                max = Math.max(max, i - j);
            } else set.remove(s.charAt(j++));
        }
        return max;
    }
}