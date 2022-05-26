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
    if (!root) return [];
    let queue = [root];
    let res = [];
    let depth = 0;
    let dir = true;
    while (queue.length) {
        let len = queue.length;
        for (let i = 0; i < len; i++) {
            let node = queue.shift();
            if (!node) continue;
            if (!res[depth]) res[depth] = [];
            if (dir) {
                res[depth].push(node.val);
            } else {
                res[depth].unshift(node.val);
            }
            queue.push(node.left, node.right);
        }
        depth++;
        dir = !dir;
    }
    return res;
};
