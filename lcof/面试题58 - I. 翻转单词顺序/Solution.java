class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        int len = words.length;
        for (int i = len - 1; i >= 0; --i) {
            if (!"".equals(words[i])) {
                sb.append(words[i]).append(" ");
            }
        }
        s = sb.toString();
        len = s.length();
        return len > 0 ? s.substring(0, len - 1) : "";
    }
}