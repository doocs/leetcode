class Solution {
    List<String> res;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        generate("", n, n);
        return res;
    }

    private void generate(String state, int left, int right) {
        if (left > right) {
            return;
        }
        if (right == 0) {
            res.add(state);
            return;
        }
        if (left > 0) {
            generate(state + "(", left - 1, right);
        }
        if (right > 0) {
            generate(state + ")", left, right - 1);
        }
    }
}