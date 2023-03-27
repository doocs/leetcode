/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {TreeNode}
 */
var lowestCommonAncestor = function (root, p, q) {
    const dfs = root => {
        if (!root) {
            return false;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (l && r) {
            ans = root;
        }
        if ((l || r) && (root.val === p.val || root.val === q.val)) {
            ans = root;
        }
        return l || r || root.val === p.val || root.val === q.val;
    };
    let ans = null;
    dfs(root);
    return ans;
};
