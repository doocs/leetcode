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
    if (root == null) {
        return root;
    }
    const queue = [root];
    while (queue.length !== 0) {
        const n = queue.length;
        let pre = null;
        for (let i = 0; i < n; i++) {
            const node = queue.shift();
            node.next = pre;
            pre = node;
            const { left, right } = node;
            right && queue.push(right);
            left && queue.push(left);
        }
    }
    return root;
}
