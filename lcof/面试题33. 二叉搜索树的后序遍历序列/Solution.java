class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int from, int to) {
        if (from == to) {
            return true;
        }
        int i = from, j = from;
        for (; i < to; ++i) {
            if (postorder[i] > postorder[to]) {
                break;
            }
        }
        for (j = i + 1; j < to; ++j) {
            if (postorder[j] < postorder[to]) {
                return false;
            }
        }
        return (i == from || verifyPostorder(postorder, from, i - 1))
                && (i == to || verifyPostorder(postorder, i, to - 1));

    }
}