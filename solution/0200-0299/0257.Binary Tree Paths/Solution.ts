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
    let ans = [];
    let t = [];
    function dfs(root) {
        if (!root) return;
        t.push(String(root.val));
        if (!root.left && !root.right) ans.push(t.join('->'));
        dfs(root.left);
        dfs(root.right);
        t.pop();
    }
    dfs(root);
    return ans;
}
