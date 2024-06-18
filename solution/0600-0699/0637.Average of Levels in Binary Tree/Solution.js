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
 * @return {number[]}
 */
var averageOfLevels = function (root) {
    const q = [root];
    const ans = [];
    while (q.length) {
        const n = q.length;
        const nq = [];
        let s = 0;
        for (const { val, left, right } of q) {
            s += val;
            left && nq.push(left);
            right && nq.push(right);
        }
        ans.push(s / n);
        q.splice(0, q.length, ...nq);
    }
    return ans;
};
