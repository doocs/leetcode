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

function getLonelyNodes(root: TreeNode | null): number[] {
    const ans: number[] = [];
    const dfs = (root: TreeNode | null) => {
        if (!root || root.left === root.right) {
            return;
        }
        if (!root.left) {
            ans.push(root.right.val);
        }
        if (!root.right) {
            ans.push(root.left.val);
        }
        dfs(root.left);
        dfs(root.right);
    };
    dfs(root);
    return ans;
}
