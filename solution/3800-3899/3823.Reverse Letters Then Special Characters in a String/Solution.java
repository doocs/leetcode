class Solution {
    public String reverseByType(String s) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        char[] t = s.toCharArray();
        for (char c : t) {
            if (Character.isLetter(c)) {
                a.append(c);
            } else {
                b.append(c);
            }
        }
        int j = a.length(), k = b.length();
        for (int i = 0; i < t.length; ++i) {
            if (Character.isLetter(t[i])) {
                t[i] = a.charAt(--j);
            } else {
                t[i] = b.charAt(--k);
            }
        }
        return new String(t);
    }
}
