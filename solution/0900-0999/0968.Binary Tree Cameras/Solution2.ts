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

function minCameraCover(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    const inf = 1 << 29;
    const sub = new Map<TreeNode, number[]>();
    const stk: [TreeNode, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (state === 0) {
            stk.push([node, 1]);
            if (node.right) {
                stk.push([node.right, 0]);
            }
            if (node.left) {
                stk.push([node.left, 0]);
            }
            continue;
        }
        const l = node.left ? sub.get(node.left)! : [inf, 0, 0];
        const r = node.right ? sub.get(node.right)! : [inf, 0, 0];
        const a = 1 + Math.min(l[0], l[1], l[2]) + Math.min(r[0], r[1], r[2]);
        const b = Math.min(l[0] + r[0], l[0] + r[1], l[1] + r[0]);
        const c = l[1] + r[1];
        sub.set(node, [a, b, c]);
    }
    const ans = sub.get(root)!;
    return Math.min(ans[0], ans[1]);
}
