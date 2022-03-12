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
    const res = [];
    const stack = [root];
    while (stack.length !== 0) {
        const root = stack.pop();
        if (root != null) {
            res.push(root.val);
            stack.push(...root.children.reverse());
        }
    }
    return res;
}
