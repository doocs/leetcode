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

function preorder(root: Node | null): number[] {
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const stk: Node[] = [root];
    while (stk.length) {
        const { val, children } = stk.pop()!;
        ans.push(val);
        for (let i = children.length - 1; i >= 0; i--) {
            stk.push(children[i]);
        }
    }
    return ans;
}
