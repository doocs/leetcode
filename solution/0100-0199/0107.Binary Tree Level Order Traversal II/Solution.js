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
    if (!root) {
        return [];
    }
    let q = [], output = [], levelOutput = [];
    q.push(root);
    q.push(null);
    while (q.length) {
        let cur = q.shift();
        levelOutput.push(cur.val);
        if (cur.left) {
            q.push(cur.left);
        }
        if (cur.right) {
            q.push(cur.right);
        }
        if (!q[0]) {
            q.shift();
            output.unshift(levelOutput);
            levelOutput = [];
            if (q.length) {
                q.push(null);
            }
        }
    }
    return output;
};