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

function isCousins(root: TreeNode | null, x: number, y: number): boolean {
    let [d1, d2] = [0, 0];
    let [p1, p2] = [null, null];
    const q: [TreeNode, TreeNode][] = [[root, null]];
    for (let depth = 0; q.length > 0; ++depth) {
        const t: [TreeNode, TreeNode][] = [];
        for (const [node, parent] of q) {
            if (node.val === x) {
                [d1, p1] = [depth, parent];
            } else if (node.val === y) {
                [d2, p2] = [depth, parent];
            }
            if (node.left) {
                t.push([node.left, node]);
            }
            if (node.right) {
                t.push([node.right, node]);
            }
        }
        q.splice(0, q.length, ...t);
    }
    return d1 === d2 && p1 !== p2;
}
