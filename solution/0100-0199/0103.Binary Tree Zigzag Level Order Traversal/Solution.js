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
    const ans = [];
    if (!root) {
        return ans;
    }
    const q = [root];
    let left = 1;
    while (q.length) {
        const t = [];
        for (let n = q.length; n; --n) {
            const node = q.shift();
            t.push(node.val);
            if (node.left) {
                q.push(node.left);
            }
            if (node.right) {
                q.push(node.right);
            }
        }
        if (!left) {
            t.reverse();
        }
        ans.push(t);
        left ^= 1;
    }
    return ans;
};
