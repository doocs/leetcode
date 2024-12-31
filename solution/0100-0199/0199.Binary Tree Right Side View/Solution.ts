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
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const q: TreeNode[] = [root];
    while (q.length > 0) {
        ans.push(q[0].val);
        const nq: TreeNode[] = [];
        for (const { left, right } of q) {
            if (right) {
                nq.push(right);
            }
            if (left) {
                nq.push(left);
            }
        }
        q.length = 0;
        q.push(...nq);
    }
    return ans;
}
