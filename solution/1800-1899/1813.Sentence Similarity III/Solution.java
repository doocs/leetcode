class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (Objects.equals(sentence1, sentence2)) {
            return true;
        }
        int n1 = sentence1.length(), n2 = sentence2.length();
        if (n1 == n2) {
            return false;
        }
        if (n1 < n2) {
            String t = sentence1;
            sentence1 = sentence2;
            sentence2 = t;
        }
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int i = 0, j = 0;
        while (i < words2.length &&  Objects.equals(words1[i], words2[i])) {
            ++i;
        }
        if (i == words2.length) {
            return true;
        }
        while (j < words2.length && Objects.equals(words1[words1.length - 1 - j], words2[words2.length - 1 - j])) {
            ++j;
        }
        if (j == words2.length) {
            return true;
        }
        return i + j == words2.length;
    }
}