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
 * @return {TreeNode}
 */
var convertBST = function (root) {
    let s = 0;
    const stk = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop();
        if (!node) {
            continue;
        }
        if (state === 0) {
            stk.push([node, 1]);
            stk.push([node.right, 0]);
            continue;
        }
        s += node.val;
        node.val = s;
        stk.push([node.left, 0]);
    }
    return root;
};
