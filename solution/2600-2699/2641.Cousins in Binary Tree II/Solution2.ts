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
    root.val = 0;
    const q: TreeNode[] = [root];
    while (q.length > 0) {
        const t: TreeNode[] = [];
        let s = 0;
        for (const { left, right } of q) {
            if (left) {
                t.push(left);
                s += left.val;
            }
            if (right) {
                t.push(right);
                s += right.val;
            }
        }
        for (const { left, right } of q) {
            const sub = (left?.val || 0) + (right?.val || 0);
            if (left) {
                left.val = s - sub;
            }
            if (right) {
                right.val = s - sub;
            }
        }
        q.splice(0, q.length, ...t);
    }
    return root;
}
