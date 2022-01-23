/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var levelOrderBottom = function (root) {
    let ans = [];
    if (!root) return ans;
    let q = [root];
    while (q.length) {
        let n = q.length;
        let t = [];
        while (n-- > 0) {
            const node = q.shift();
            t.push(node.val);
            if (node.left) q.push(node.left);
            if (node.right) q.push(node.right);
        }
        ans.unshift(t);
    }
    return ans;
};
