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

function pathSum(root: TreeNode | null, target: number): number[][] {
    const res = [];
    if (root == null) {
        return res;
    }
    const dfs = (
        { val, right, left }: TreeNode,
        target: number,
        values: number[]
    ) => {
        values.push(val);
        target -= val;
        if (left == null && right == null) {
            if (target === 0) {
                res.push(values);
            }
            return;
        }
        left && dfs(left, target, [...values]);
        right && dfs(right, target, [...values]);
    };
    dfs(root, target, []);
    return res;
}
