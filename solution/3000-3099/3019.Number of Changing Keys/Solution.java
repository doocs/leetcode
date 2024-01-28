class Solution {
    public int countKeyChanges(String s) {
        int n = s.length();
        int count = 0;
        String lowerCaseString = s.toLowerCase();
        for (int i = 0; i < n - 1; i++) {
            if (lowerCaseString.charAt(i) != lowerCaseString.charAt(i + 1)) {
                count++;
            }
        }
        return count;
    }
}
