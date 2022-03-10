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

function levelOrder(root: TreeNode | null): number[] {
    const res = [];
    if (root == null) {
        return res;
    }

    const arr = [root];
    let i = 0;
    while (i < arr.length) {
        const { val, left, right } = arr[i];
        res.push(val);
        left && arr.push(left);
        right && arr.push(right);
        i++;
    }
    return res;
}
