class Solution {
    public String solveEquation(String equation) {
        String[] es = equation.split("=");
        int[] a = f(es[0]), b = f(es[1]);
        int x1 = a[0], y1 = a[1];
        int x2 = b[0], y2 = b[1];
        if (x1 == x2) {
            return y1 == y2 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (y2 - y1) / (x1 - x2);
    }

    private int[] f(String s) {
        int x = 0, y = 0;
        if (s.charAt(0) != '-') {
            s = "+" + s;
        }
        int i = 0, n = s.length();
        while (i < n) {
            int sign = s.charAt(i) == '+' ? 1 : -1;
            ++i;
            int j = i;
            while (j < n && s.charAt(j) != '+' && s.charAt(j) != '-') {
                ++j;
            }
            String v = s.substring(i, j);
            if (s.charAt(j - 1) == 'x') {
                x += sign * (v.length() > 1 ? Integer.parseInt(v.substring(0, v.length() - 1)) : 1);
            } else {
                y += sign * Integer.parseInt(v);
            }
            i = j;
        }
        return new int[] {x, y};
    }
}