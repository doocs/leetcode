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

    for (const [parent, child] of descriptions) {
        if (!nodes[parent]) nodes[parent] = new TreeNode(parent);
        if (!nodes[child]) nodes[child] = new TreeNode(child);

        children.add(child);
    }

    let root = -1;
    for (const [parent, child, isLeft] of descriptions) {
        if (!children.has(parent)) root = parent;

        if (isLeft) nodes[parent].left = nodes[child];
        else nodes[parent].right = nodes[child];
    }

    return nodes[root];
};
