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
    this.stk = [];
    this.cur = root;
}


/**
 * @return {number}
 */
BSTIterator.prototype.next = function () {
    while (this.cur) {
        this.stk.push(this.cur);
        this.cur = this.cur.left;
    }
    this.cur = this.stk.pop();
    let res = this.cur.val;
    this.cur = this.cur.right;
    return (res);
};

/**
 * @return {boolean}
 */
BSTIterator.prototype.hasNext = function () {
    if (this.stk.length === 0 && this.cur === null) return false;
    return true;
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */