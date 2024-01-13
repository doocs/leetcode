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

function deepestLeavesSum(root: TreeNode | null): number {
    let res = 0;
    let maxDepath = 0;
    const dfs = ({ val, left, right }: TreeNode, depth: number) => {
        if (left == null && right == null) {
            if (depth === maxDepath) {
                res += val;
            } else if (depth > maxDepath) {
                maxDepath = depth;
                res = val;
            }
            return;
        }
        left && dfs(left, depth + 1);
        right && dfs(right, depth + 1);
    };
    dfs(root, 0);
    return res;
}
