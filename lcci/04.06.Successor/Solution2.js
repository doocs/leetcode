/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @return {TreeNode}
 */
var inorderSuccessor = function (root, p) {
    const stack = [];
    let cur = root;
    while (cur != null || stack.length !== 0) {
        if (cur == null) {
            cur = stack.pop();
            if (cur.val > p.val) {
                return cur;
            }
            cur = cur.right;
        } else {
            stack.push(cur);
            cur = cur.left;
        }
    }
    return cur;
};
