public class Solution {
    public bool VerifyPostorder(int[] postorder) {
        int n = postorder.Length;
        int[] stk = new int[(n + 2) * 2];
        int top = 0;
        stk[top++] = 0;
        stk[top++] = n - 1;
        while (top > 0) {
            int r = stk[--top];
            int l = stk[--top];
            if (l >= r) {
                continue;
            }
            int v = postorder[r];
            int i = l;
            while (i < r && postorder[i] < v) {
                ++i;
            }
            for (int j = i; j < r; ++j) {
                if (postorder[j] < v) {
                    return false;
                }
            }
            stk[top++] = i;
            stk[top++] = r - 1;
            stk[top++] = l;
            stk[top++] = i - 1;
        }
        return true;
    }
}
