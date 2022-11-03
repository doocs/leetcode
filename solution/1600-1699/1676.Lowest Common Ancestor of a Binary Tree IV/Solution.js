/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode[]} nodes
 * @return {TreeNode}
 */
var lowestCommonAncestor = function (root, nodes) {
    const s = new Set();
    for (const node of nodes) {
        s.add(node.val);
    }
    function dfs(root) {
        if (!root || s.has(root.val)) {
            return root;
        }
        const [left, right] = [dfs(root.left), dfs(root.right)];
        if (left && right) {
            return root;
        }
        return left || right;
    }
    return dfs(root);
};
