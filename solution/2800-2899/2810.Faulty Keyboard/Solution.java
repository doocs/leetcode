class Solution {
    public String finalString(String s) {
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                t.reverse();
            } else {
                t.append(c);
            }
        }
        return t.toString();
    }
}