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
    const d = new Map();
    const n = inorder.length;
    for (let i = 0; i < n; ++i) {
        d.set(inorder[i], i);
    }
    const dfs = (i, j, n) => {
        if (n <= 0) {
            return null;
        }
        const v = preorder[i];
        const k = d.get(v);
        const l = dfs(i + 1, j, k - j);
        const r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
};
