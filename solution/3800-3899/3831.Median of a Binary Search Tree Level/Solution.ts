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
function levelMedian(root: TreeNode | null, level: number): number {
    const nums: number[] = [];

    const dfs = (node: TreeNode | null, i: number): void => {
        if (node === null) {
            return;
        }
        dfs(node.left, i + 1);
        if (i === level) {
            nums.push(node.val);
        }
        dfs(node.right, i + 1);
    };

    dfs(root, 0);
    if (nums.length === 0) {
        return -1;
    }
    return nums[nums.length >> 1];
}
