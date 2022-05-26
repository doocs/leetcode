class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        String[] c1 = num1.split("\\+|i");
        String[] c2 = num2.split("\\+|i");
        int a = Integer.parseInt(c1[0]);
        int b = Integer.parseInt(c1[1]);
        int c = Integer.parseInt(c2[0]);
        int d = Integer.parseInt(c2[1]);
        return String.format("%d+%di", a * c - b * d, a * d + c * b);
    }
}