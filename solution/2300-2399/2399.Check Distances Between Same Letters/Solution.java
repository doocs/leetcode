class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int[] d = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (d[j] > 0 && i - d[j] != distance[j]) {
                return false;
            }
            d[j] = i + 1;
        }
        return true;
    }
}