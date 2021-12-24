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
    if (!preorder || !preorder.length) return null;
    let preIdx = 0;
    let inMap = {};
    for (let i = 0; i < inorder.length; i++) {
        inMap[inorder[i]] = i;
    }
    function func(start, end) {
        if (start > end) {
            return null;
        }
        let preVal = preorder[preIdx];
        preIdx++;
        let inIdx = inMap[preVal];
        let node = new TreeNode(preVal);
        node.left = func(start, inIdx - 1);
        node.right = func(inIdx + 1, end);
        return node;
    }
    return func(0, preorder.length - 1);
};
