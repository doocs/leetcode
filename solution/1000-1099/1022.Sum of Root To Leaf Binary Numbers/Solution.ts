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

function sumRootToLeaf(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, num: number) => {
        if (root == null) {
            return 0;
        }
        const { val, left, right } = root;
        num = (num << 1) | val;
        if (left == null && right == null) {
            return num;
        }
        return dfs(left, num) + dfs(right, num);
    };
    return dfs(root, 0);
}
