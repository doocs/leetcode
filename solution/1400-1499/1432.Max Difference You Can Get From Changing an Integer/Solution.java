class Solution {
    public int maxDiff(int num) {
        String a = String.valueOf(num);
        String b = a;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != '9') {
                a = a.replace(a.charAt(i), '9');
                break;
            }
        }
        if (b.charAt(0) != '1') {
            b = b.replace(b.charAt(0), '1');
        } else {
            for (int i = 1; i < b.length(); ++i) {
                if (b.charAt(i) != '0' && b.charAt(i) != '1') {
                    b = b.replace(b.charAt(i), '0');
                    break;
                }
            }
        }
        return Integer.parseInt(a) - Integer.parseInt(b);
    }
}