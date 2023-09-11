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

function delNodes(root: TreeNode | null, to_delete: number[]): Array<TreeNode | null> {
    const s: boolean[] = Array(1001).fill(false);
    for (const x of to_delete) {
        s[x] = true;
    }
    const ans: Array<TreeNode | null> = [];
    const dfs = (root: TreeNode | null): TreeNode | null => {
        if (!root) {
            return null;
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        if (!s[root.val]) {
            return root;
        }
        if (root.left) {
            ans.push(root.left);
        }
        if (root.right) {
            ans.push(root.right);
        }
        return null;
    };
    if (dfs(root)) {
        ans.push(root);
    }
    return ans;
}
