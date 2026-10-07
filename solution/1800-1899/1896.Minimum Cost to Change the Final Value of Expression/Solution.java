class Solution {
    public int minOperationsToFlip(String expression) {
        int n = expression.length();
        int[] val = new int[n];
        int[] cost = new int[n];
        int top = 0;
        char[] ops = new char[n];
        int otop = 0;
        for (int i = 0; i < n; ++i) {
            char c = expression.charAt(i);
            if (c == '(') {
                ops[otop++] = c;
            } else if (c == '0' || c == '1') {
                val[top] = c - '0';
                cost[top++] = 1;
            } else if (c == '&' || c == '|') {
                while (otop > 0 && (ops[otop - 1] == '&' || ops[otop - 1] == '|')) {
                    merge(val, cost, top, ops[--otop]);
                    --top;
                }
                ops[otop++] = c;
            } else {
                while (ops[otop - 1] != '(') {
                    merge(val, cost, top, ops[--otop]);
                    --top;
                }
                --otop;
            }
        }
        while (otop > 0) {
            merge(val, cost, top, ops[--otop]);
            --top;
        }
        return cost[0];
    }

    private void merge(int[] val, int[] cost, int top, char op) {
        int v1 = val[top - 2], c1 = cost[top - 2];
        int v2 = val[top - 1], c2 = cost[top - 1];
        if (op == '&') {
            val[top - 2] = v1 & v2;
            if (v1 == 1 && v2 == 1) {
                cost[top - 2] = Math.min(c1, c2);
            } else if (v1 == 0 && v2 == 0) {
                cost[top - 2] = Math.min(c1 + c2, 1 + Math.min(c1, c2));
            } else {
                cost[top - 2] = Math.min(v1 == 0 ? c1 : c2, 1);
            }
        } else {
            val[top - 2] = v1 | v2;
            if (v1 == 0 && v2 == 0) {
                cost[top - 2] = Math.min(c1, c2);
            } else if (v1 == 1 && v2 == 1) {
                cost[top - 2] = Math.min(c1 + c2, 1 + Math.min(c1, c2));
            } else {
                cost[top - 2] = Math.min(v1 == 1 ? c1 : c2, 1);
            }
        }
    }
}
