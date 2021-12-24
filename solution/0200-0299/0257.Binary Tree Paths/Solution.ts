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
    let pre = "";
    dfs(root, pre, ans);
    return ans;
}

function dfs(root: TreeNode | null, pre: string, ans: string[]): void {
    if (root == null) return;
    let val = String(root.val);
    pre = pre.length > 0 ? `${pre}->${val}` : pre + val;
    // 叶子节点
    if (root.left == null && root.right == null) {
        ans.push(pre);
        return;
    }
    dfs(root.left, pre, ans);
    dfs(root.right, pre, ans);
}
