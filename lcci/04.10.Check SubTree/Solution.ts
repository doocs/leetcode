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

function checkSubTree(t1: TreeNode | null, t2: TreeNode | null): boolean {
    const dfs = (t1: TreeNode | null, t2: TreeNode | null): boolean => {
        if (!t2) {
            return !t1;
        }
        if (!t1 || t1.val !== t2.val) {
            return false;
        }
        return dfs(t1.left, t2.left) && dfs(t1.right, t2.right);
    };
    if (!t2) {
        return true;
    }
    if (!t1) {
        return false;
    }
    if (dfs(t1, t2)) {
        return true;
    }
    return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
}
