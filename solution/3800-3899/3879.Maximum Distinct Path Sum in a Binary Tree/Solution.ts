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
function maxSum(root: TreeNode | null): number {
    const g = new Map<TreeNode, (TreeNode | null)[]>();

    function dfs(node: TreeNode | null, p: TreeNode | null): void {
        if (!node) return;
        if (!g.has(node)) g.set(node, []);
        g.get(node)!.push(p, node.left, node.right);
        dfs(node.left, node);
        dfs(node.right, node);
    }

    const vis = new Set<number>();

    function dfs2(node: TreeNode | null): number {
        if (!node || vis.has(node.val)) return 0;
        vis.add(node.val);
        let res = node.val;
        let best = 0;
        for (const nxt of g.get(node) || []) {
            best = Math.max(best, dfs2(nxt));
        }
        vis.delete(node.val);
        return res + best;
    }

    dfs(root, null);

    let ans = -Infinity;
    for (const node of g.keys()) {
        ans = Math.max(ans, dfs2(node));
        vis.clear();
    }
    return ans;
}
