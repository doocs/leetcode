class Solution {
    public String replaceSpace(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            ans.append(c == ' ' ? "%20" : c);
        }
        return ans.toString();
    }
}