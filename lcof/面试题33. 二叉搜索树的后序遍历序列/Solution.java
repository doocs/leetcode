class Solution {
    public boolean verifyPostorder(int[] postorder) {
        int n;
        if (postorder == null || (n = postorder.length) == 0) return true;
        return verify(postorder, 0, n - 1);
    }

    private boolean  verify(int[] postorder, int p1, int p2) {
        if (p1 >= p2) return true;
        int pos = p1;
        while (pos < p2 && postorder[pos] < postorder[p2]) ++pos;
        int p = pos;
        while (pos < p2) {
            if (postorder[pos] < postorder[p2]) return false;
            ++pos;
        }
        return verify(postorder, p1, p - 1) && verify(postorder, p, p2 - 1);
    }
}