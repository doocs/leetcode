class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] x = parse(num1);
        int[] y = parse(num2);
        int a1 = x[0], b1 = x[1], a2 = y[0], b2 = y[1];
        return (a1 * a2 - b1 * b2) + "+" + (a1 * b2 + a2 * b1) + "i";
    }

    private int[] parse(String s) {
        var cs = s.substring(0, s.length() - 1).split("\\+");
        return new int[] {Integer.parseInt(cs[0]), Integer.parseInt(cs[1])};
    }
}