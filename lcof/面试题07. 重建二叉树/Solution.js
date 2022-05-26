/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function (preorder, inorder) {
    if (preorder.length == 0) return null;
    const v = preorder[0];
    const root = new TreeNode(v);
    const i = inorder.indexOf(v);
    root.left = buildTree(preorder.slice(1, 1 + i), inorder.slice(0, i));
    root.right = buildTree(preorder.slice(1 + i), inorder.slice(1 + i));
    return root;
};
