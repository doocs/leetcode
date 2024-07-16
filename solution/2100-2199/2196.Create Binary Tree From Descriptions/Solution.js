/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[][]} descriptions
 * @return {TreeNode}
 */
var createBinaryTree = function (descriptions) {
    const nodes = {};
    const children = new Set();
    for (const [parent, child, isLeft] of descriptions) {
        if (!nodes[parent]) {
            nodes[parent] = new TreeNode(parent);
        }
        if (!nodes[child]) {
            nodes[child] = new TreeNode(child);
        }
        if (isLeft) {
            nodes[parent].left = nodes[child];
        } else {
            nodes[parent].right = nodes[child];
        }
        children.add(child);
    }
    for (const [k, v] of Object.entries(nodes)) {
        if (!children.has(+k)) {
            return v;
        }
    }
};
