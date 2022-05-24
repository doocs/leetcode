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
    const res: number[][] = [];
    if (root == null) {
        return res;
    }
    const paths: number[] = [];
    const dfs = ({ val, right, left }: TreeNode, target: number) => {
        paths.push(val);
        target -= val;
        if (left == null && right == null) {
            if (target === 0) {
                res.push([...paths]);
            }
        } else {
            left && dfs(left, target);
            right && dfs(right, target);
        }
        paths.pop();
    };
    dfs(root, target);
    return res;
}
