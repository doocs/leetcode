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

function sumOfLeftLeaves(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    let ans = 0;
    const stk: TreeNode[] = [root];
    while (stk.length) {
        const { left, right } = stk.pop()!;
        if (left) {
            if (left.left === left.right) {
                ans += left.val;
            } else {
                stk.push(left);
            }
        }
        if (right) {
            stk.push(right);
        }
    }
    return ans;
}
