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

function sumEvenGrandparent(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, x: number): number => {
        if (!root) {
            return 0;
        }
        const { val, left, right } = root;
        let ans = dfs(left, val) + dfs(right, val);
        if (x % 2 === 0) {
            ans += left?.val ?? 0;
            ans += right?.val ?? 0;
        }
        return ans;
    };
    return dfs(root, 1);
}
