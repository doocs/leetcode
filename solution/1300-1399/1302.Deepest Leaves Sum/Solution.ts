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

function deepestLeavesSum(root: TreeNode | null): number {
    let q: TreeNode[] = [root];
    let ans = 0;
    while (q.length) {
        const nq: TreeNode[] = [];
        ans = 0;
        for (const { val, left, right } of q) {
            ans += val;
            left && nq.push(left);
            right && nq.push(right);
        }
        q = nq;
    }
    return ans;
}
