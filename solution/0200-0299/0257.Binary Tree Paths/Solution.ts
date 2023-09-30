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

function binaryTreePaths(root: TreeNode | null): string[] {
    const ans: string[] = [];
    const t: number[] = [];
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        t.push(root.val);
        if (!root.left && !root.right) {
            ans.push(t.join('->'));
        } else {
            dfs(root.left);
            dfs(root.right);
        }
        t.pop();
    };
    dfs(root);
    return ans;
}
