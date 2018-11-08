class Solution {
	List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generateParenthesis(n, n, new char[n * 2], 0);
        return result;
    }
    void generateParenthesis(int left, int right, char[] cache, int index) {
        if (right == 0) {
            result.add(String.valueOf(cache));
            return;
        }
        if (left > 0) {
            cache[index] = '(';
            generateParenthesis(left - 1, right, cache, index + 1);
        }
        if (right > left) {
            cache[index] = ')';
            generateParenthesis(left, right - 1, cache, index + 1);
        }
    }
}
