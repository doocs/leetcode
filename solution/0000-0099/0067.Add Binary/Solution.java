class Solution {
    public String addBinary(String a, String b) {
        int al = a.length(), bl = b.length(), di = Math.max(al, bl);
        char[] ac = a.toCharArray(), bc = b.toCharArray(), re = new char[di + 1];
        boolean jw = false;
        for (int i = 1; i <= di; i++) {
            int alc = al - i , blc = bl - i;
            char aChar = alc >= 0 ? ac[alc] : '0';
            char bChar = blc >= 0 ? bc[blc] : '0';
            if (aChar == bChar) {
                re[di + 1 - i] = jw ? '1' : '0';
                jw = aChar == '1';
            } else re[di + 1 - i] = jw ? '0' : '1';
        }
        if (jw) re[0] = '1';
        StringBuilder builder = new StringBuilder();
        for (char c : re) if (c != 0) builder.append(c);
        return builder.toString();
    }
}