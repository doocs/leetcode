class Solution {
    public int minMaxDifference(int num) {
        String s = String.valueOf(num);
        int mi = Integer.parseInt(s.replace(s.charAt(0), '0'));
        for (char c : s.toCharArray()) {
            if (c != '9') {
                return Integer.parseInt(s.replace(c, '9')) - mi;
            }
        }
        return num - mi;
    }
}