class Solution {
    public String decodeMessage(String key, String message) {
        char[] d = new char[128];
        d[' '] = ' ';
        for (int i = 0, j = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (d[c] == 0) {
                d[c] = (char) ('a' + j++);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < message.length(); ++i) {
            ans.append(d[message.charAt(i)]);
        }
        return ans.toString();
    }
}