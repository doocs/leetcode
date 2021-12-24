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
var zigzagLevelOrder = function (root) {
    if (!root) {
        return [];
    }
    let ans = [];
    let q = [root];
    let left = false;
    while (q.length) {
        let t = [];
        for (let i = 0, n = q.length; i < n; ++i) {
            const node = q.shift();
            t.push(node.val);
            if (node.left) {
                q.push(node.left);
            }
            if (node.right) {
                q.push(node.right);
            }
        }
        if (left) {
            t.reverse();
        }
        ans.push(t);
        left = !left;
    }
    return ans;
};
