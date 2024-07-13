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

function closestValue(root: TreeNode | null, target: number): number {
    let ans = 0;
    let diff = Number.POSITIVE_INFINITY;

    while (root) {
        const nxt = Math.abs(root.val - target);
        if (nxt < diff || (nxt === diff && root.val < ans)) {
            diff = nxt;
            ans = root.val;
        }
        root = target < root.val ? root.left : root.right;
    }
    return ans;
}
