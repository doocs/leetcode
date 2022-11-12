/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function (preorder, inorder) {
    function dfs(i, j, n) {
        if (n <= 0) {
            return null;
        }
        const v = preorder[i];
        const k = d[v];
        const root = new TreeNode(v);
        root.left = dfs(i + 1, j, k - j);
        root.right = dfs(i + 1 + k - j, k + 1, n - k + j - 1);
        return root;
    }
    const d = new Map();
    for (const [i, v] of inorder.entries()) {
        d[v] = i;
    }
    return dfs(0, 0, inorder.length);
};
