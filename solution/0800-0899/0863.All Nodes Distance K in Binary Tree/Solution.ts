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
    const g = new Map<TreeNode, TreeNode | null>();
    const ans: number[] = [];

    const dfs = (node: TreeNode | null, fa: TreeNode | null) => {
        if (!node) {
            return;
        }
        g.set(node, fa);
        dfs(node.left, node);
        dfs(node.right, node);
    };

    const dfs2 = (node: TreeNode | null, fa: TreeNode | null, k: number) => {
        if (!node) {
            return;
        }
        if (k === 0) {
            ans.push(node.val);
            return;
        }
        for (const nxt of [node.left, node.right, g.get(node) || null]) {
            if (nxt !== fa) {
                dfs2(nxt, node, k - 1);
            }
        }
    };

    dfs(root, null);
    dfs2(target, null, k);
    return ans;
}
