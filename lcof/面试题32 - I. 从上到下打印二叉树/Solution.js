/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var levelOrder = function (root) {
    if (!root) {
        return [];
    }
    const q = [root];
    const ans = [];
    while (q.length) {
        for (let n = q.length; n; --n) {
            const { val, left, right } = q.shift();
            ans.push(val);
            left && q.push(left);
            right && q.push(right);
        }
    }
    return ans;
};
