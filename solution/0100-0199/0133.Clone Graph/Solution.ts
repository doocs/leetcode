/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     neighbors: Node[]
 *     constructor(val?: number, neighbors?: Node[]) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.neighbors = (neighbors===undefined ? [] : neighbors)
 *     }
 * }
 */

function cloneGraph(node: Node | null): Node | null {
    if (node == null) return null;

    const visited = new Map();
    visited.set(node, new Node(node.val));
    const queue = [node];
    while (queue.length) {
        const cur = queue.shift();
        for (let neighbor of cur.neighbors || []) {
            if (!visited.has(neighbor)) {
                queue.push(neighbor);
                const newNeighbor = new Node(neighbor.val, []);
                visited.set(neighbor, newNeighbor);
            }
            const newNode = visited.get(cur);
            newNode.neighbors.push(visited.get(neighbor));
        }
    }
    return visited.get(node);
}
