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
    const ans = [];
    if (!root) {
        return ans;
    }
    const q = [root];
    while (q.length) {
        const t = [];
        for (let n = q.length; n; --n) {
            const { val, left, right } = q.shift();
            t.push(val);
            left && q.push(left);
            right && q.push(right);
        }
        if (ans.length & 1) {
            t.reverse();
        }
        ans.push(t);
    }
    return ans;
};
