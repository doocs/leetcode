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
    if (root == null) {
        return res;
    }
    const stack = [root];
    while (stack.length !== 0) {
        const { val, children } = stack.pop();
        res.push(val);
        const n = children.length;
        for (let i = n - 1; i >= 0; i--) {
            stack.push(children[i]);
        }
    }
    return res;
}
