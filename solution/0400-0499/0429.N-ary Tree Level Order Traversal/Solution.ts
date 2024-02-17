/**
 * Definition for node.
 * class Node {
 *     val: number
 *     children: Node[]
 *     constructor(val?: number) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.children = []
 *     }
 * }
 */

function levelOrder(root: Node | null): number[][] {
    const ans: number[][] = [];
    if (!root) {
        return ans;
    }
    const q: Node[] = [root];
    while (q.length) {
        const qq: Node[] = [];
        const t: number[] = [];
        for (const { val, children } of q) {
            qq.push(...children);
            t.push(val);
        }
        ans.push(t);
        q.splice(0, q.length, ...qq);
    }
    return ans;
}
