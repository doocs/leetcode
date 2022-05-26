/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function sortedArrayToBST(nums: number[]): TreeNode | null {
    const dfs = (start: number, end: number): TreeNode | null => {
        if (start >= end) {
            return null;
        }
        const mid = Math.floor(start + (end - start) / 2);
        return new TreeNode(nums[mid], dfs(start, mid), dfs(mid + 1, end));
    };
    return dfs(0, nums.length);
}
