class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] split = str.split(" ");
        if (pattern.length() != split.length) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            if (!map1.getOrDefault(c, -1).equals(map2.getOrDefault(split[i], -1))) {
                return false;
            }
            map1.put(c, i);
            map2.put(split[i], i);
        }
        return true;
    }
}
