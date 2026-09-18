/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     random: Node | null
 *     constructor(val?: number, left?: Node | null, right?: Node | null, random?: Node | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomBinaryTree(root: Node | null): NodeCopy | null {
    const seen = new Map<Node, NodeCopy>();
    const dfs = (root: Node | null): NodeCopy | null => {
        if (root === null) {
            return null;
        }
        if (seen.has(root)) {
            return seen.get(root)!;
        }
        const copy = new NodeCopy(root.val);
        seen.set(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    };
    return dfs(root);
}
