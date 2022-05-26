class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length - 1, carry = 0;
        LinkedList<Integer> ans = new LinkedList<>();
        while (i >= 0 || k > 0 || carry > 0) {
            carry += (i < 0 ? 0 : num[i--]) + k % 10;
            ans.addFirst(carry % 10);
            carry /= 10;
            k /= 10;
        }
        return ans;
    }
}