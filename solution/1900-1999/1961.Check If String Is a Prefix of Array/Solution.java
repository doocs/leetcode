class Solution {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder t = new StringBuilder();
        for (String w : words) {
            t.append(w);
            if (s.length() == t.length()) {
                return s.equals(t.toString());
            }
        }
        return false;
    }
}