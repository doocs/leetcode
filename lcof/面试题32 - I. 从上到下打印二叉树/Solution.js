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
    const ans = [];
    if (!root) {
        return ans;
    }
    const q = [root];
    while (q.length) {
        const t = [];
        for (const { val, left, right } of q) {
            ans.push(val);
            left && t.push(left);
            right && t.push(right);
        }
        q.splice(0, q.length, ...t);
    }
    return ans;
};
