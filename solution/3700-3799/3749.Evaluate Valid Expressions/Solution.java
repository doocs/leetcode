class Solution {
    private String expression;

    public long evaluateExpression(String expression) {
        this.expression = expression;
        return parse(0)[0];
    }

    private long[] parse(int i) {
        if (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '-') {
            int j = i;
            if (expression.charAt(j) == '-') {
                j++;
            }
            while (j < expression.length() && Character.isDigit(expression.charAt(j))) {
                j++;
            }
            long num = Long.parseLong(expression.substring(i, j));
            return new long[] {num, j};
        }

        int j = i;
        while (expression.charAt(j) != '(') {
            j++;
        }
        String op = expression.substring(i, j);
        j++;

        long[] result1 = parse(j);
        long val1 = result1[0];
        j = (int) result1[1];
        j++;

        long[] result2 = parse(j);
        long val2 = result2[0];
        j = (int) result2[1];
        j++;

        long res = 0;
        switch (op) {
        case "add":
            res = val1 + val2;
            break;
        case "sub":
            res = val1 - val2;
            break;
        case "mul":
            res = val1 * val2;
            break;
        case "div":
            res = val1 / val2;
            break;
        }

        return new long[] {res, j};
    }
}
