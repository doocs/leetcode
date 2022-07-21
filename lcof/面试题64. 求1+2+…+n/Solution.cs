public class Solution {
    public int result;
    public int SumNums(int n) {
        helper(n);
        return result;
    }

    public bool helper(int n) {
        result += n;
        return n == 0 || helper(n - 1);
    }
}