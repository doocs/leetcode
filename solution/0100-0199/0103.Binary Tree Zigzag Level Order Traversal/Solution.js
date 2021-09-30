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
    let res = [], q = [];
    q.push(root);
    let leftToRight = true;
    while (q.length) {
        let levelSize = q.length, levelOutput = [];
        for (let i = 0; i < levelSize; i++) {
            let cur = q.shift();
            if (cur.left) {
                q.push(cur.left);
            }
            if (cur.right) {
                q.push(cur.right);
            }
            if (leftToRight) {
                levelOutput.push(cur.val);
            } else {
                levelOutput.unshift(cur.val);
            }
        }
        res.push(levelOutput);
        leftToRight = !leftToRight;
    }
    return res;
};