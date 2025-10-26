class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9;
        sum %= 9;
        StringBuilder ans = new StringBuilder("9".repeat(k));
        if (sum > 0) {
            ans.append(sum);
        }
        ans.append("0".repeat(num - ans.length()));
        return ans.toString();
    }
}
