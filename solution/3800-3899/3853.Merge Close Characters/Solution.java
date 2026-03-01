class Solution {
    public String mergeCharacters(String s, int k) {
        Map<Character, Integer> last = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int cur = ans.length();
            if (last.containsKey(c) && cur - last.get(c) <= k) {
                continue;
            }
            ans.append(c);
            last.put(c, cur);
        }
        return ans.toString();
    }
}
