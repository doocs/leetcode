class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int shortestDistance = wordsDict.length;
        for (int i = 0; i < wordsDict.length; ++i) {
            if (word1.equals(wordsDict[i])) {
                i1 = i;
            } else if (word2.equals(wordsDict[i])) {
                i2 = i;
            }
            if (i1 != -1 && i2 != -1) {
                shortestDistance = Math.min(shortestDistance, Math.abs(i1 - i2));
            }
        }
        return shortestDistance;
    }
}