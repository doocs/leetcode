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
var CBTInserter = function (root) {
    this.tree = [];
    if (root === null) {
        return;
    }
    const q = [root];
    while (q.length > 0) {
        const t = [];
        for (const node of q) {
            this.tree.push(node);
            node.left !== null && t.push(node.left);
            node.right !== null && t.push(node.right);
        }
        q.splice(0, q.length, ...t);
    }
};

/**
 * @param {number} val
 * @return {number}
 */
CBTInserter.prototype.insert = function (val) {
    const p = this.tree[(this.tree.length - 1) >> 1];
    const node = new TreeNode(val);
    this.tree.push(node);
    if (p.left === null) {
        p.left = node;
    } else {
        p.right = node;
    }
    return p.val;
};

/**
 * @return {TreeNode}
 */
CBTInserter.prototype.get_root = function () {
    return this.tree[0];
};

/**
 * Your CBTInserter object will be instantiated and called as such:
 * var obj = new CBTInserter(root)
 * var param_1 = obj.insert(val)
 * var param_2 = obj.get_root()
 */
