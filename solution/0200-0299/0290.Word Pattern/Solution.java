class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] ss = s.split(" ");
        int n = pattern.length();
        if (n != ss.length) {
            return false;
        }
        Map<Character, String> c2str = new HashMap<>();
        Map<String, Character> str2c = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char k = pattern.charAt(i);
            String v = ss[i];
            if (c2str.containsKey(k) && !Objects.equals(c2str.get(k), v)) {
                return false;
            }
            if (str2c.containsKey(v) && !Objects.equals(str2c.get(v), k)) {
                return false;
            }
            c2str.put(k, v);
            str2c.put(v, k);
        }
        return true;
    }
}