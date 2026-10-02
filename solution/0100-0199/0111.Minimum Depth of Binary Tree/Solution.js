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
 * @return {number}
 */
var minDepth = function (root) {
    if (!root) {
        return 0;
    }
    const queue = [root];
    let head = 0;
    let depth = 1;
    while (head < queue.length) {
        const end = queue.length;
        for (; head < end; ++head) {
            const node = queue[head];
            if (!node.left && !node.right) {
                return depth;
            }
            if (node.left) {
                queue.push(node.left);
            }
            if (node.right) {
                queue.push(node.right);
            }
        }
        ++depth;
    }
    return 0;
};
