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
var maxPathSum = function (root) {
    let ans = -1001;
    if (!root) {
        return ans;
    }
    const stack = [root];
    const postorder = [];
    while (stack.length > 0) {
        const node = stack.pop();
        postorder.push(node);
        if (node.left) {
            stack.push(node.left);
        }
        if (node.right) {
            stack.push(node.right);
        }
    }
    const gains = new Map();
    while (postorder.length > 0) {
        const node = postorder.pop();
        const left = node.left ? Math.max(0, gains.get(node.left)) : 0;
        const right = node.right ? Math.max(0, gains.get(node.right)) : 0;
        ans = Math.max(ans, left + right + node.val);
        gains.set(node, Math.max(left, right) + node.val);
    }
    return ans;
};
