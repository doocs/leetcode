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
    let q = [root];
    let ans = [];
    while (q.length) {
        const n = q.length;
        let s = 0;
        for (let i = 0; i < n; ++i) {
            root = q.shift();
            s += root.val;
            if (root.left) {
                q.push(root.left);
            }
            if (root.right) {
                q.push(root.right);
            }
        }
        ans.push(s / n);
    }
    return ans;
};
