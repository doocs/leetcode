/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} nums
 * @return {TreeNode}
 */
 var sortedArrayToBST = function(nums) {
  const buildBST = (nums, start, end) => {
      if (start > end) {
          return null;
      }
      const mid = (start + end) >> 1;
      const root = new TreeNode(nums[mid]);
      root.left = buildBST(nums, start, mid - 1);
      root.right = buildBST(nums, mid + 1, end);
      return root;
  }

  return buildBST(nums, 0, nums.length - 1);
};