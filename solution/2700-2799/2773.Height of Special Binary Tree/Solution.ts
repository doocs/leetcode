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

function heightOfTree(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (root: TreeNode | null, d: number) => {
        ans = Math.max(ans, d++);
        if (root.left && root.left.right !== root) {
            dfs(root.left, d);
        }
        if (root.right && root.right.left !== root) {
            dfs(root.right, d);
        }
    };
    dfs(root, 0);
    return ans;
}
