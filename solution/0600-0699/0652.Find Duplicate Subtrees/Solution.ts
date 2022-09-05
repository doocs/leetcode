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

function findDuplicateSubtrees(root: TreeNode | null): Array<TreeNode | null> {
    const map = new Map<string, number>();
    const res = [];
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return '#';
        }
        const { val, left, right } = root;
        const s = `${val},${dfs(left)},${dfs(right)}`;
        map.set(s, (map.get(s) ?? 0) + 1);
        if (map.get(s) === 2) {
            res.push(root);
        }
        return s;
    };
    dfs(root);
    return res;
}
