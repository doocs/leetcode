class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0) return null;
        return helper(nums, 0, len - 1);
    }
    private TreeNode helper(int[] arr, int start, int end) {
        if (start > end) return null;
        int middle = (end + start) / 2;
        TreeNode helperRoot = new TreeNode(arr[middle]);
        helperRoot.left = helper(arr, start, middle - 1);
        helperRoot.right = helper(arr, middle + 1, end);
        return helperRoot;
    }
}