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
    const q = [root];
    while (q.length) {
        const node = q.shift();
        this.tree.push(node);
        if (node.left) {
            q.push(node.left);
        }
        if (node.right) {
            q.push(node.right);
        }
    }
};

/**
 * @param {number} v
 * @return {number}
 */
CBTInserter.prototype.insert = function (v) {
    const pid = (this.tree.length - 1) >> 1;
    const node = new TreeNode(v);
    this.tree.push(node);
    const p = this.tree[pid];
    if (!p.left) {
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
 * var param_1 = obj.insert(v)
 * var param_2 = obj.get_root()
 */
