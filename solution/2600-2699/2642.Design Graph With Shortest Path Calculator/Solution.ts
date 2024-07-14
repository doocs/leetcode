class Graph {
    private g: number[][] = [];

    constructor(n: number, edges: number[][]) {
        this.g = Array.from({ length: n }, () => Array(n).fill(Infinity));
        for (const [f, t, c] of edges) {
            this.g[f][t] = c;
        }
    }

    addEdge(edge: number[]): void {
        const [f, t, c] = edge;
        this.g[f][t] = c;
    }

    shortestPath(node1: number, node2: number): number {
        const n = this.g.length;
        const dist: number[] = Array(n).fill(Infinity);
        dist[node1] = 0;
        const vis: boolean[] = Array(n).fill(false);
        for (let i = 0; i < n; ++i) {
            let t = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (t === -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + this.g[t][j]);
            }
        }
        return dist[node2] >= Infinity ? -1 : dist[node2];
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * var obj = new Graph(n, edges)
 * obj.addEdge(edge)
 * var param_2 = obj.shortestPath(node1,node2)
 */
