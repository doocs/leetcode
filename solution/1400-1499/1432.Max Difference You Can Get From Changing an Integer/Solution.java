class Solution {
    public int maxDiff(int num) {
        String a = String.valueOf(num);
        String b = String.valueOf(num);
        for (char c : a.toCharArray()) {
            if (c != '9') {
                a = a.replaceAll(String.valueOf(c), "9");
                break;
            }
        }
        for (int i = 0; i < b.length(); ++i) {
            char c = b.charAt(i);
            if (i == 0) {
                if (c != '1') {
                    b = b.replaceAll(String.valueOf(c), "1");
                    break;
                }
            } else {
                if (c != '0' && c != b.charAt(0)) {
                    b = b.replaceAll(String.valueOf(c), "0");
                    break;
                }
            }
        }
        return Integer.parseInt(a) - Integer.parseInt(b);
    }
}