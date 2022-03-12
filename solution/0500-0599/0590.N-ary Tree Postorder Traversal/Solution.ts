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
    const dfs = (root: Node | null) => {
        if (root == null) {
            return;
        }
        for (const node of root.children) {
            dfs(node);
        }
        res.push(root.val);
    };
    dfs(root);
    return res;
}
