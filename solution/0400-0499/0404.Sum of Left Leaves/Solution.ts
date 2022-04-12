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

const dfs = (root: TreeNode | null, isLeft: boolean) => {
    let res = 0;
    const { val, left, right } = root;
    if (left == null && right == null) {
        if (isLeft) {
            return val;
        }
        return res;
    }
    if (left != null) {
        res += dfs(left, true);
    }
    if (right != null) {
        res += dfs(right, false);
    }
    return res;
};

function sumOfLeftLeaves(root: TreeNode | null): number {
    return dfs(root, false);
}
