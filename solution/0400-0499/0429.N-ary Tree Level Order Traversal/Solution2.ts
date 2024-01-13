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
    const res = [];
    const dfs = (root: Node | null, depth: number) => {
        if (root == null) {
            return;
        }
        if (res.length <= depth) {
            res.push([]);
        }
        const { val, children } = root;
        res[depth].push(val);
        children.forEach(node => dfs(node, depth + 1));
    };
    dfs(root, 0);
    return res;
}
