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

function kthLargestLevelSum(root: TreeNode | null, k: number): number {
    const dfs = (root: TreeNode, d: number) => {
        if (!root) {
            return;
        }
        if (arr.length <= d) {
            arr.push(0);
        }
        arr[d] += root.val;
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    };
    const arr: number[] = [];
    dfs(root, 0);
    if (arr.length < k) {
        return -1;
    }
    arr.sort((a, b) => b - a);
    return arr[k - 1];
}
