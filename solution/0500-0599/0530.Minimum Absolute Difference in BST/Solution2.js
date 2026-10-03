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
var getMinimumDifference = function (root) {
    let ans = Infinity;
    let pre = -Infinity;
    const stk = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop();
        if (!node) {
            continue;
        }
        if (state === 0) {
            stk.push([node, 1]);
            stk.push([node.left, 0]);
            continue;
        }
        ans = Math.min(ans, node.val - pre);
        pre = node.val;
        stk.push([node.right, 0]);
    }
    return ans;
};
