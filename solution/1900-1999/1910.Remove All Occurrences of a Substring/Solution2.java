class Solution {
    public String removeOccurrences(String s, String part) {
        int m = part.length();
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            st.append(s.charAt(i));
            if (st.length() >= m && st.substring(st.length() - m).equals(part)) {
                st.setLength(st.length() - m);
            }
        }
        return st.toString();
    }
}
