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

function flipMatchVoyage(root: TreeNode | null, voyage: number[]): number[] {
    let ok = true;
    let i = 0;
    const ans: number[] = [];
    const dfs = (root: TreeNode | null): void => {
        if (!root || !ok) {
            return;
        }
        if (root.val !== voyage[i++]) {
            ok = false;
            return;
        }
        if (!root.left || root.left.val === voyage[i]) {
            dfs(root.left);
            dfs(root.right);
        } else {
            ans.push(root.val);
            dfs(root.right);
            dfs(root.left);
        }
    };
    dfs(root);
    return ok ? ans : [-1];
}
