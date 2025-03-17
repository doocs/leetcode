/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     neighbors: _Node[]
 *
 *     constructor(val?: number, neighbors?: _Node[]) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.neighbors = (neighbors===undefined ? [] : neighbors)
 *     }
 * }
 *
 */

function cloneGraph(node: _Node | null): _Node | null {
    const g: Map<_Node, _Node> = new Map();
    const dfs = (node: _Node | null): _Node | null => {
        if (!node) {
            return null;
        }
        if (g.has(node)) {
            return g.get(node);
        }
        const cloned = new _Node(node.val);
        g.set(node, cloned);
        for (const nxt of node.neighbors) {
            cloned.neighbors.push(dfs(nxt));
        }
        return cloned;
    };
    return dfs(node);
}
