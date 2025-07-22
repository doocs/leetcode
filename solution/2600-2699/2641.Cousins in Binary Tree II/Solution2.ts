/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function replaceValueInTree(root: TreeNode | null): TreeNode | null {
    let q = [root];
    let [sum, nextSum] = [0, root.val];

    while (q.length) {
        const qNext: TreeNode[] = [];
        [sum, nextSum] = [nextSum, 0];

        for (const node of q) {
            const x = (node.left?.val ?? 0) + (node.right?.val ?? 0);
            node.val = sum - node.val;
            nextSum += x;

            if (node.left) {
                node.left.val = x;
                qNext.push(node.left);
            }

            if (node.right) {
                node.right.val = x;
                qNext.push(node.right);
            }
        }

        q = qNext;
    }

    return root;
}
