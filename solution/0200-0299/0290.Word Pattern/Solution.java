class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> ch2str = new HashMap<>();
        Map<String, Character> str2ch = new HashMap<>();
        String[] ss = s.split(" ");
        int n = pattern.length();
        if (n != ss.length) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            char ch = pattern.charAt(i);
            if (ch2str.containsKey(ch) && !ch2str.get(ch).equals(ss[i])) {
                return false;
            }
            if (str2ch.containsKey(ss[i]) && !str2ch.get(ss[i]).equals(ch)) {
                return false;
            }
            ch2str.put(ch, ss[i]);
            str2ch.put(ss[i], ch);
        }
        return true;
    }
}