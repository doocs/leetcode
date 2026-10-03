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
    const g: Map<number, number[]> = new Map();
    const stk: [TreeNode | null, TreeNode | null][] = [[root, null]];
    while (stk.length) {
        const [node, fa] = stk.pop()!;
        if (!node) {
            continue;
        }
        if (fa) {
            if (!g.has(node.val)) {
                g.set(node.val, []);
            }
            g.get(node.val)!.push(fa.val);
            if (!g.has(fa.val)) {
                g.set(fa.val, []);
            }
            g.get(fa.val)!.push(node.val);
        }
        stk.push([node.right, node]);
        stk.push([node.left, node]);
    }
    const dist = new Map<number, number>();
    const walk: [number, number, number][] = [[start, -1, 0]];
    while (walk.length) {
        const [node, fa, state] = walk.pop()!;
        const nxts = g.get(node) || [];
        if (state === 0) {
            walk.push([node, fa, 1]);
            for (let i = nxts.length - 1; i >= 0; --i) {
                const nxt = nxts[i];
                if (nxt !== fa) {
                    walk.push([nxt, node, 0]);
                }
            }
            continue;
        }
        let best = 0;
        for (const nxt of nxts) {
            if (nxt !== fa) {
                best = Math.max(best, 1 + dist.get(nxt)!);
            }
        }
        dist.set(node, best);
    }
    return dist.get(start)!;
}
