/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     next: Node | null
 *     constructor(val?: number, left?: Node, right?: Node, next?: Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function connect(root: Node | null): Node | null {
    const modify = (curr: Node | null): void => {
        if (!curr) {
            return;
        }
        next = next || curr;
        if (prev) {
            prev.next = curr;
        }
        prev = curr;
    };
    let node = root;
    let [prev, next] = [null, null];
    while (node) {
        while (node) {
            modify(node.left);
            modify(node.right);
            node = node.next;
        }
        node = next;
        [prev, next] = [null, null];
    }
    return root;
}
