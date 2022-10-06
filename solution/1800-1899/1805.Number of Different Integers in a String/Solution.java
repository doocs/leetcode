class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> s = new HashSet<>();
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(word.charAt(i))) {
                while (i < n && word.charAt(i) == '0') {
                    ++i;
                }
                int j = i;
                while (j < n && Character.isDigit(word.charAt(j))) {
                    ++j;
                }
                s.add(word.substring(i, j));
                i = j;
            }
        }
        return s.size();
    }
}