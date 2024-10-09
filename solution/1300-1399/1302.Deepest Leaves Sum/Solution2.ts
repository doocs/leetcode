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

function deepestLeavesSum(root: TreeNode | null): number {
    let [ans, mx] = [0, 0];
    const dfs = (root: TreeNode | null, i: number) => {
        if (!root) {
            return;
        }
        if (i > mx) {
            mx = i;
            ans = root.val;
        } else if (i === mx) {
            ans += root.val;
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    };
    dfs(root, 1);
    return ans;
}
