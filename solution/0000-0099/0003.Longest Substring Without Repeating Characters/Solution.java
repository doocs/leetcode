class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int fast = 0, slow = 0; fast < s.length(); fast ++) {
            if (map.containsKey(s.charAt(fast))) {
                int target = map.get(s.charAt(fast)) + 1;
                slow = target < slow ? slow : target;
            }
            map.put(s.charAt(fast), fast);
            max = Math.max(max, fast - slow + 1);
        }
        return max;
    }
}