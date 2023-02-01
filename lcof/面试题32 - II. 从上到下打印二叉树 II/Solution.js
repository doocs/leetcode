/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var levelOrder = function (root) {
    let ans = [];
    if (!root) {
        return ans;
    }
    let q = [root];
    while (q.length) {
        let t = [];
        for (let n = q.length; n; --n) {
            const { val, left, right } = q.shift();
            t.push(val);
            left && q.push(left);
            right && q.push(right);
        }
        ans.push(t);
    }
    return ans;
};
