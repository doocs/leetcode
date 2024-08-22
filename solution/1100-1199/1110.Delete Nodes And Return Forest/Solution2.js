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
 * @param {number[]} to_delete
 * @return {TreeNode[]}
 */
var delNodes = function (root, to_delete) {
    if (!root) return [];

    const del = new Set(to_delete);
    const res = [];
    let q = [root];

    while (q.length) {
        const qNext = [];

        for (const node of q) {
            if (node.left) {
                qNext.push(node.left);

                if (del.has(node.left.val)) {
                    node.left = null;
                }
            }

            if (node.right) {
                qNext.push(node.right);

                if (del.has(node.right.val)) {
                    node.right = null;
                }
            }

            if (del.has(node.val)) {
                if (node.left) res.push(node.left);
                if (node.right) res.push(node.right);
            }
        }

        q = qNext;
    }

    if (!del.has(root.val)) res.push(root);

    return res;
};
