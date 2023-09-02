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

function getTargetCopy(
    original: TreeNode | null,
    cloned: TreeNode | null,
    target: TreeNode | null,
): TreeNode | null {
    const dfs = (root1: TreeNode | null, root2: TreeNode | null): TreeNode | null => {
        if (!root1) {
            return null;
        }
        if (root1 === target) {
            return root2;
        }
        return dfs(root1.left, root2.left) || dfs(root1.right, root2.right);
    };
    return dfs(original, cloned);
}
