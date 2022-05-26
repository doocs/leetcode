class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) {
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
        n1 = words1.length;
        n2 = words2.length;
        while (i < n2 &&  words1[i].equals(words2[i])) {
            ++i;
        }
        if (i == n2) {
            return true;
        }
        while (j < n2 && words1[n1 - 1 - j].equals(words2[n2 - 1 - j])) {
            ++j;
        }
        return j == n2 || i + j == n2;
    }
}
