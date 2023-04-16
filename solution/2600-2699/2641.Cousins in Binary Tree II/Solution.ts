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

function replaceValueInTree(root: TreeNode | null): TreeNode | null {
    const s: number[] = [];
    const dfs1 = (root: TreeNode | null, d: number): void => {
        if (!root) {
            return;
        }
        if (s.length <= d) {
            s.push(0);
        }
        s[d] += root.val;
        dfs1(root.left, d + 1);
        dfs1(root.right, d + 1);
    };
    const dfs2 = (root: TreeNode | null, d: number): void => {
        if (!root) {
            return;
        }
        const t = (root.left?.val ?? 0) + (root.right?.val ?? 0);
        if (root.left) {
            root.left.val = s[d] - t;
        }
        if (root.right) {
            root.right.val = s[d] - t;
        }
        dfs2(root.left, d + 1);
        dfs2(root.right, d + 1);
    };
    dfs1(root, 0);
    root.val = 0;
    dfs2(root, 1);
    return root;
}
