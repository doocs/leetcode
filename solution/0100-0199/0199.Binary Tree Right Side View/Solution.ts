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
    if (!root) {
        return [];
    }
    let q = [root];
    const ans: number[] = [];
    while (q.length) {
        const nextq: TreeNode[] = [];
        ans.push(q.at(-1)!.val);
        for (const { left, right } of q) {
            if (left) {
                nextq.push(left);
            }
            if (right) {
                nextq.push(right);
            }
        }
        q = nextq;
    }
    return ans;
}
