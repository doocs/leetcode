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

function distanceK(root: TreeNode | null, target: TreeNode | null, k: number): number[] {
    if (!root) return [0];

    const g: Record<number, number[]> = {};

    const dfs = (node: TreeNode | null, parent: TreeNode | null = null) => {
        if (!node) return;

        g[node.val] ??= [];
        if (parent) g[node.val].push(parent.val);
        if (node.left) g[node.val].push(node.left.val);
        if (node.right) g[node.val].push(node.right.val);

        dfs(node.left, node);
        dfs(node.right, node);
    };

    dfs(root);

    const vis = new Set<number>();
    let q = [target!.val];

    while (q.length) {
        if (!k--) return q;

        const nextQ: number[] = [];

        for (const x of q) {
            if (vis.has(x)) continue;

            vis.add(x);
            nextQ.push(...g[x].filter(x => !vis.has(x)));
        }

        q = nextQ;
    }

    return [];
}
