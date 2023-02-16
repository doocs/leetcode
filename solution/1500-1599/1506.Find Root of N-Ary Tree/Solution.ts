/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     children: Node[]
 *     constructor(val?: number, children?: Node[]) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.children = (children===undefined ? [] : children)
 *     }
 * }
 */

function findRoot(tree: Node[]): Node | null {
    let x = 0;
    for (const node of tree) {
        x ^= node.val;
        for (const child of node.children) {
            x ^= child.val;
        }
    }
    return tree.find(node => node.val === x) || null;
}
