public class Solution {
    public bool VerifyPostorder(int[] postorder) {
        if (postorder.Length == 0) {
            return true;
        }
        var root = postorder[^1];
        int n = postorder.Length, i = 0;
        while (i < n && postorder[i] < root) {
            i += 1;
        }
        for (int j = i; j < n - 1; j++) {
            if (postorder[j] < root) {
                return false;
            }
        }
        return VerifyPostorder(postorder[..i]) && VerifyPostorder(postorder[i..^1]);
    }
}