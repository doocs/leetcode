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

function pathSum(root: TreeNode | null, target: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];

    const dfs = (root: TreeNode | null, s: number): void => {
        if (!root) {
            return;
        }
        const { val, left, right } = root;
        t.push(val);
        s -= val;
        if (!left && !right && s === 0) {
            ans.push([...t]);
        }
        dfs(left, s);
        dfs(right, s);
        t.pop();
    };

    dfs(root, target);
    return ans;
}
