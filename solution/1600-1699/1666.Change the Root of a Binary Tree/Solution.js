/**
 * // Definition for a Node.
 * function Node(val) {
 *    this.val = val;
 *    this.left = null;
 *    this.right = null;
 *    this.parent = null;
 * };
 */

/**
 * @param {Node} node
 * @return {Node}
 */
var flipBinaryTree = function (root, leaf) {
    let cur = leaf;
    let p = cur.parent;
    while (cur != root) {
        const gp = p.parent;
        if (cur.left != null) {
            cur.right = cur.left;
        }
        cur.left = p;
        p.parent = cur;
        if (p.left == cur) {
            p.left = null;
        } else if (p.right == cur) {
            p.right = null;
        }
        cur = p;
        p = gp;
    }
    leaf.parent = null;
    return leaf;
};
