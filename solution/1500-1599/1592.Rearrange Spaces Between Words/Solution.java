class Solution {
    public String reorderSpaces(String text) {
        int spaces = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                ++spaces;
            }
        }
        String[] words = text.trim().split("\\s+");
        if (words.length == 1) {
            return words[0] + " ".repeat(spaces);
        }
        int cnt = spaces / (words.length - 1);
        int mod = spaces % (words.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; ++i) {
            sb.append(words[i]);
            if (i < words.length - 1) {
                sb.append(" ".repeat(cnt));
            }
        }
        sb.append(" ".repeat(mod));
        return sb.toString();
    }
}
