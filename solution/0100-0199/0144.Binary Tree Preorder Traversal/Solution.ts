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

function preorderTraversal(root: TreeNode | null): number[] {
    let ans = [];
    if (!root) return ans;
    let stk = [root];
    while (stk.length) {
        let node = stk.pop();
        ans.push(node.val);
        if (node.right) stk.push(node.right);
        if (node.left) stk.push(node.left);
    }
    return ans;
}
