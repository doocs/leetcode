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
    const ans = [];
    const dfs = (root: Node | null) => {
        if (root == null) {
            return;
        }
        ans.push(root.val);
        for (const node of root.children) {
            dfs(node);
        }
    };
    dfs(root);
    return ans;
}
