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

function longestUnivaluePath(root: TreeNode | null): number {
    if (root == null) {
        return 0;
    }

    let res = 0;
    const dfs = (root: TreeNode | null, target: number) => {
        if (root == null) {
            return 0;
        }

        const { val, left, right } = root;

        let l = dfs(left, val);
        let r = dfs(right, val);
        res = Math.max(res, l + r);
        if (val === target) {
            return Math.max(l, r) + 1;
        }
        return 0;
    };
    dfs(root, root.val);
    return res;
}
