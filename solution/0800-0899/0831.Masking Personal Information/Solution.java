class Solution {
    public String maskPII(String s) {
        if (Character.isLetter(s.charAt(0))) {
            s = s.toLowerCase();
            int i = s.indexOf('@');
            return s.substring(0, 1) + "*****" + s.substring(i - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        s = sb.toString();
        int cnt = s.length() - 10;
        String suf = "***-***-" + s.substring(s.length() - 4);
        return cnt == 0 ? suf
                        : "+"
                + "*".repeat(cnt) + "-" + suf;
    }
}