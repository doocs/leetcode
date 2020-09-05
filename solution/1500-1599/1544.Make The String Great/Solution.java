class Solution {
    public String makeGood(String s) {
        return help(s);
    }

    private String help(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (Math.abs(chars[i] - chars[i - 1]) == Math.abs('A' - 'a')) {
                return help(newString(chars, i));
            }
        }
        return s;
    }

    private String newString(char[] chars, int i) {
        StringBuilder tmp = new StringBuilder();
        for (int j = 0; j < chars.length; j++) {
            if (j != i && j != i - 1) {
                tmp.append(chars[j]);
            }
        }
        return tmp.toString();
    }
}