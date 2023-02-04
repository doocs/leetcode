class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        var words = s.split("\\s+");
        for (int i = 0, j = words.length - 1; i < j; ++i, --j) {
            var t = words[i];
            words[i] = words[j];
            words[j] = t;
        }
        return String.join(" ", words);
    }
}