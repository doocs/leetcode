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

function amountOfTime(root: TreeNode | null, start: number): number {
    const g: Map<number, number[]> = new Map();
    const dfs = (node: TreeNode | null, fa: TreeNode | null) => {
        if (!node) {
            return;
        }
        if (fa) {
            if (!g.has(node.val)) {
                g.set(node.val, []);
            }
            g.get(node.val)!.push(fa.val);
            if (!g.has(fa.val)) {
                g.set(fa.val, []);
            }
            g.get(fa.val)!.push(node.val);
        }
        dfs(node.left, node);
        dfs(node.right, node);
    };
    const dfs2 = (node: number, fa: number): number => {
        let ans = 0;
        for (const nxt of g.get(node) || []) {
            if (nxt !== fa) {
                ans = Math.max(ans, 1 + dfs2(nxt, node));
            }
        }
        return ans;
    };
    dfs(root, null);
    return dfs2(start, -1);
}
