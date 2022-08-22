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

function amountOfTime(root: TreeNode | null, start: number): number {
    const map = new Map<number, number[]>();
    const create = ({ val, left, right }: TreeNode) => {
        if (left != null) {
            map.set(val, [...(map.get(val) ?? []), left.val]);
            map.set(left.val, [...(map.get(left.val) ?? []), val]);
            create(left);
        }
        if (right != null) {
            map.set(val, [...(map.get(val) ?? []), right.val]);
            map.set(right.val, [...(map.get(right.val) ?? []), val]);
            create(right);
        }
    };
    create(root);
    const dfs = (st: number, fa: number) => {
        let res = 0;
        for (const v of map.get(st) ?? []) {
            if (v !== fa) {
                res = Math.max(res, dfs(v, st) + 1);
            }
        }
        return res;
    };
    return dfs(start, -1);
}
