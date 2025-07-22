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

function findFrequentTreeSum(root: TreeNode | null): number[] {
    const cnt = new Map<number, number>();
    let mx = 0;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const { val, left, right } = root;
        const s = val + dfs(left) + dfs(right);
        cnt.set(s, (cnt.get(s) ?? 0) + 1);
        mx = Math.max(mx, cnt.get(s)!);
        return s;
    };
    dfs(root);
    return Array.from(cnt.entries())
        .filter(([_, c]) => c === mx)
        .map(([s, _]) => s);
}
