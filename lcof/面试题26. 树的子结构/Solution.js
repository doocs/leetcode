/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} A
 * @param {TreeNode} B
 * @return {boolean}
 */
var isSubStructure = function (A, B) {
    function sub(A, B) {
        if (!B) return true;
        if (!A) return false;
        return A.val == B.val && sub(A.left, B.left) && sub(A.right, B.right);
    }
    if (!B || !A) return false;
    if (A.val != B.val)
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    return sub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
};
