class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length - 1;
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        while (n >= 0 || K != 0 || carry != 0) {
            carry += (n < 0 ? 0 : A[n]) + (K % 10);
            res.add(carry % 10);
            K /= 10;
            carry /= 10;
            --n;
        }
        Collections.reverse(res);
        return res;
    }
}