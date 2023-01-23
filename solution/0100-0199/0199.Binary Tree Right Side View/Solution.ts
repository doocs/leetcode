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

function rightSideView(root: TreeNode | null): number[] {
    const ans = [];
    if (!root) {
        return ans;
    }
    const q = [root];
    while (q.length) {
        const n = q.length;
        ans.push(q[n - 1].val);
        for (let i = 0; i < n; ++i) {
            const { left, right } = q.shift();
            left && q.push(left);
            right && q.push(right);
        }
    }
    return ans;
}
