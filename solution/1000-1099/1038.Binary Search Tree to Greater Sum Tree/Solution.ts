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

function bstToGst(root: TreeNode | null): TreeNode | null {
    const dfs = (root: TreeNode | null, sum: number) => {
        if (root == null) {
            return sum;
        }
        const { val, left, right } = root;
        sum = dfs(right, sum) + val;
        root.val = sum;
        sum = dfs(left, sum);
        return sum;
    };
    dfs(root, 0);
    return root;
}
