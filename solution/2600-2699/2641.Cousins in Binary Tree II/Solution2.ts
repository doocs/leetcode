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
    let q: TreeNode[] = [root];
    while (q.length) {
        const p: TreeNode[] = q;
        q = [];
        let s: number = 0;
        for (const { left, right } of p) {
            if (left) {
                q.push(left);
                s += left.val;
            }
            if (right) {
                q.push(right);
                s += right.val;
            }
        }
        for (const { left, right } of p) {
            const t: number = (left?.val ?? 0) + (right?.val ?? 0);
            if (left) {
                left.val = s - t;
            }
            if (right) {
                right.val = s - t;
            }
        }
    }
    return root;
}
