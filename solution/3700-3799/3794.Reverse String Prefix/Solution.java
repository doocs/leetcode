class Solution {
    public String reversePrefix(String s, int k) {
        StringBuilder sb = new StringBuilder(s.substring(0, k));
        return sb.reverse().toString() + s.substring(k);
    }
}
