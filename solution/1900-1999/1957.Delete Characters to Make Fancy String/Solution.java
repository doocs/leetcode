class Solution {
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = ans.length();
            if (n < 2 || c != ans.charAt(n - 1) || c != ans.charAt(n - 2)) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
