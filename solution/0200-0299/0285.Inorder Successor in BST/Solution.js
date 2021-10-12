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
 var inorderSuccessor = function(root, p) {
    let cur = root;
    let ans = null;
    while (cur != null) {
        if (cur.val <= p.val) {
            cur = cur.right;
        } else {
            ans = cur;
            cur = cur.left;
        }
    }
    return ans;
};