class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        for (int i = A.length - 1; i >= 0 && K != 0; --i) {
            K += A[i];
            A[i] = K % 10;
            K /= 10;
        }
        List<Integer> res = new ArrayList<>();
        while (K != 0) {
            res.add(K % 10);
            K /= 10;
        }
        Collections.reverse(res);
        for (int a : A) {
            res.add(a);
        }
        return res;
    }
}
