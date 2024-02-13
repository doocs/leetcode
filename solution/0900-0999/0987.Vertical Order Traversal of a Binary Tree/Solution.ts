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

function verticalTraversal(root: TreeNode | null): number[][] {
    const nodes: [number, number, number][] = [];
    const dfs = (root: TreeNode | null, i: number, j: number) => {
        if (!root) {
            return;
        }
        nodes.push([j, i, root.val]);
        dfs(root.left, i + 1, j - 1);
        dfs(root.right, i + 1, j + 1);
    };
    dfs(root, 0, 0);
    nodes.sort((a, b) => a[0] - b[0] || a[1] - b[1] || a[2] - b[2]);
    const ans: number[][] = [];
    let prev = -2000;
    for (const [j, _, val] of nodes) {
        if (j !== prev) {
            prev = j;
            ans.push([]);
        }
        ans.at(-1)!.push(val);
    }
    return ans;
}
