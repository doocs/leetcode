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
 */
var BSTIterator = function (root) {
    this.cur = 0;
    this.vals = [];
    const inorder = root => {
        if (root) {
            inorder(root.left);
            this.vals.push(root.val);
            inorder(root.right);
        }
    };
    inorder(root);
};

/**
 * @return {number}
 */
BSTIterator.prototype.next = function () {
    return this.vals[this.cur++];
};

/**
 * @return {boolean}
 */
BSTIterator.prototype.hasNext = function () {
    return this.cur < this.vals.length;
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
