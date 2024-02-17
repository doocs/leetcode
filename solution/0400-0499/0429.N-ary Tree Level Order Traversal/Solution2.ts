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
    const ans: number[][] = [];
    const dfs = (root: Node | null, i: number) => {
        if (root === null) {
            return;
        }
        if (ans.length <= i) {
            ans.push([]);
        }
        const { val, children } = root;
        ans[i++].push(val);
        children.forEach(node => dfs(node, i));
    };
    dfs(root, 0);
    return ans;
}
