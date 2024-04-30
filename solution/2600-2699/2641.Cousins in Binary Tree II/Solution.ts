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
    const dfs1 = (root: TreeNode | null, depth: number) => {
        if (!root) {
            return;
        }
        if (s.length <= depth) {
            s.push(0);
        }
        s[depth] += root.val;
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    };
    const dfs2 = (root: TreeNode | null, depth: number) => {
        const sub = (root.left?.val || 0) + (root.right?.val || 0);
        ++depth;
        if (root.left) {
            root.left.val = s[depth] - sub;
            dfs2(root.left, depth);
        }
        if (root.right) {
            root.right.val = s[depth] - sub;
            dfs2(root.right, depth);
        }
    };
    dfs1(root, 0);
    root.val = 0;
    dfs2(root, 0);
    return root;
}
