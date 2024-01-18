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

function largestValues(root: TreeNode | null): number[] {
    const res = [];
    const dfs = (root: TreeNode | null, depth: number) => {
        if (root == null) {
            return;
        }
        const { val, left, right } = root;
        if (res.length == depth) {
            res.push(val);
        } else {
            res[depth] = Math.max(res[depth], val);
        }
        dfs(left, depth + 1);
        dfs(right, depth + 1);
    };
    dfs(root, 0);
    return res;
}
