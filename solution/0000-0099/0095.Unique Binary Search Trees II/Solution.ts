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

function generateTrees(n: number): Array<TreeNode | null> {
    const dfs = (i: number, j: number): Array<TreeNode | null> => {
        if (i > j) {
            return [null];
        }
        const ans: Array<TreeNode | null> = [];
        for (let v = i; v <= j; ++v) {
            const left = dfs(i, v - 1);
            const right = dfs(v + 1, j);
            for (const l of left) {
                for (const r of right) {
                    ans.push(new TreeNode(v, l, r));
                }
            }
        }
        return ans;
    };
    return dfs(1, n);
}
