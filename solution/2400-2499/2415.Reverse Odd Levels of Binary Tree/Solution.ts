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

function reverseOddLevels(root: TreeNode | null): TreeNode | null {
    const queue = [root];
    let d = 0;
    while (queue.length !== 0) {
        const n = queue.length;
        const t: TreeNode[] = [];
        for (let i = 0; i < n; i++) {
            const node = queue.shift();
            if (d % 2 == 1) {
                t.push(node);
            }
            node.left && queue.push(node.left);
            node.right && queue.push(node.right);
        }
        const m = t.length;
        for (let i = 0; i < m >> 1; i++) {
            [t[i].val, t[m - 1 - i].val] = [t[m - 1 - i].val, t[i].val];
        }
        d++;
    }
    return root;
}
