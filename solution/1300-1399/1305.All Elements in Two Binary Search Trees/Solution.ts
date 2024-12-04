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

function getAllElements(root1: TreeNode | null, root2: TreeNode | null): number[] {
    const dfs = (root: TreeNode | null, nums: number[]) => {
        if (!root) {
            return;
        }
        dfs(root.left, nums);
        nums.push(root.val);
        dfs(root.right, nums);
    };
    const a: number[] = [];
    const b: number[] = [];
    dfs(root1, a);
    dfs(root2, b);
    const [m, n] = [a.length, b.length];
    const ans: number[] = [];
    let [i, j] = [0, 0];
    while (i < m && j < n) {
        if (a[i] < b[j]) {
            ans.push(a[i++]);
        } else {
            ans.push(b[j++]);
        }
    }
    while (i < m) {
        ans.push(a[i++]);
    }
    while (j < n) {
        ans.push(b[j++]);
    }
    return ans;
}
