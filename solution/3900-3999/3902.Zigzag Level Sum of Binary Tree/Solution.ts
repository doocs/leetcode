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
function zigzagLevelSum(root: TreeNode | null): number[] {
    let q: TreeNode[] = [root];
    const ans: number[] = [];
    let left = true;
    while (q.length > 0) {
        const nq: TreeNode[] = [];
        for (const { left, right } of q) {
            if (left !== null) {
                nq.push(left);
            }
            if (right !== null) {
                nq.push(right);
            }
        }
        const m = q.length;
        let s = 0;
        for (let i = 0; i < m; i++) {
            const node = left ? q[i] : q[m - i - 1];
            const child = left ? node.left : node.right;
            if (child === null) {
                break;
            }
            s += node.val;
        }
        ans.push(s);
        left = !left;
        q = nq;
    }
    return ans;
}
