/**
 * Definition for a binary tree node.
 * function Node(val, left, right) {
 *     this.val = (val===undefined ? " " : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {Node} root1
 * @param {Node} root2
 * @return {boolean}
 */
var checkEquivalence = function (root1, root2) {
    const cnt = new Array(26).fill(0);
    const dfs = (root, v) => {
        if (!root) {
            return;
        }
        if (root.val !== '+') {
            cnt[root.val.charCodeAt(0) - 'a'.charCodeAt(0)] += v;
        }
        dfs(root.left, v);
        dfs(root.right, v);
    };
    dfs(root1, 1);
    dfs(root2, -1);
    for (const x of cnt) {
        if (x) {
            return false;
        }
    }
    return true;
};
