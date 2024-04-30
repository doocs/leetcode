class Solution {
    public int findString(String[] words, String s) {
        return dfs(words, s, 0, words.length - 1);
    }
    
    private int dfs(String[] words, String s, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >> 1;
        int l = dfs(words, s, i, mid - 1);
        if (l != -1) {
            return l;
        }
        if (words[mid].equals(s)) {
            return mid;
        }
        return dfs(words, s, mid + 1, j);
    }
}