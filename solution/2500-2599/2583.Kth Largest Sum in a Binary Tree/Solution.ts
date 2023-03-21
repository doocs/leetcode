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

function kthLargestLevelSum(root: TreeNode | null, k: number): number {
    const arr: number[] = [];
    const q = [root];
    while (q.length) {
        let t = 0;
        for (let n = q.length; n > 0; --n) {
            root = q.shift();
            t += root.val;
            if (root.left) {
                q.push(root.left);
            }
            if (root.right) {
                q.push(root.right);
            }
        }
        arr.push(t);
    }
    if (arr.length < k) {
        return -1;
    }
    arr.sort((a, b) => b - a);
    return arr[k - 1];
}
