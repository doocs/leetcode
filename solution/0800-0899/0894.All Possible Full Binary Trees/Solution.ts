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

function allPossibleFBT(n: number): Array<TreeNode | null> {
    const f: Array<Array<TreeNode | null>> = new Array(n + 1).fill(0).map(() => []);
    const dfs = (n: number): Array<TreeNode | null> => {
        if (f[n].length) {
            return f[n];
        }
        if (n === 1) {
            f[n].push(new TreeNode(0));
            return f[n];
        }
        const ans: Array<TreeNode | null> = [];
        for (let i = 0; i < n - 1; ++i) {
            const j = n - 1 - i;
            for (const left of dfs(i)) {
                for (const right of dfs(j)) {
                    ans.push(new TreeNode(0, left, right));
                }
            }
        }
        return (f[n] = ans);
    };
    return dfs(n);
}
