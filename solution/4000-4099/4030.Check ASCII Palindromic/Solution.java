class Solution {
    public boolean isPalindromic(String s) {
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            String b = Integer.toBinaryString(c);
            t.append("0".repeat(8 - b.length())).append(b);
        }
        return t.toString().equals(t.reverse().toString());
    }
}
