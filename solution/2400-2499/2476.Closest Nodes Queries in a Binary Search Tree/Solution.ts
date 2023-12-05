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

function closestNodes(root: TreeNode | null, queries: number[]): number[][] {
    const nums: number[] = [];
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        dfs(root.left);
        nums.push(root.val);
        dfs(root.right);
    };
    const search = (x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    dfs(root);
    const ans: number[][] = [];
    for (const x of queries) {
        const i = search(x + 1) - 1;
        const j = search(x);
        const mi = i >= 0 ? nums[i] : -1;
        const mx = j < nums.length ? nums[j] : -1;
        ans.push([mi, mx]);
    }
    return ans;
}
