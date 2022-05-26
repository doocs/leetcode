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
    if (!root) return [];
    let queue = [root];
    let res = [];
    while (queue.length) {
        let node = queue.shift();
        if (!node) continue;
        res.push(node.val);
        queue.push(node.left, node.right);
    }
    return res;
};
