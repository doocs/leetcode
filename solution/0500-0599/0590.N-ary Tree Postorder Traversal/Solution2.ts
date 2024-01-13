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

function postorder(root: Node | null): number[] {
    const res = [];
    if (root == null) {
        return res;
    }
    const stack = [root];
    while (stack.length !== 0) {
        const target = stack[stack.length - 1];
        if (target.children == null) {
            res.push(stack.pop().val);
        } else {
            for (let i = target.children.length - 1; i >= 0; i--) {
                stack.push(target.children[i]);
            }
            target.children = null;
        }
    }
    return res;
}
