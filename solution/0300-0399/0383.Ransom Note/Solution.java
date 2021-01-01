class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        for (int i = 0, n = magazine.length(); i < n; ++i) {
            int idx = magazine.charAt(i) - 'a';
            ++chars[idx];
        }
        for (int i = 0, n = ransomNote.length(); i < n; ++i) {
            int idx = ransomNote.charAt(i) - 'a';
            if (chars[idx] == 0) return false;
            --chars[idx];
        }
        return true;
    }
}