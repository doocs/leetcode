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
    if (!root) {
        return 0;
    }
    const { val, left, right } = root;
    if (!left && !right) {
        if (isLeft) {
            return val;
        }
        return 0;
    }
    return dfs(left, true) + dfs(right, false);
};

function sumOfLeftLeaves(root: TreeNode | null): number {
    return dfs(root, false);
}
