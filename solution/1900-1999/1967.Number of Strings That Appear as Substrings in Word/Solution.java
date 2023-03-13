class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String p : patterns) {
            if (word.contains(p)) {
                ++ans;
            }
        }
        return ans;
    }
}