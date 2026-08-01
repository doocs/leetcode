class Edge {
    to: number;
    cap: number;
    cost: number;
    rev: number;

    constructor(to: number, cap: number, cost: number, rev: number) {
        this.to = to;
        this.cap = cap;
        this.cost = cost;
        this.rev = rev;
    }
}

class MinCostMaxFlow {
    private n: number;
    private graph: Edge[][];

    static readonly INF = 1e9;

    constructor(n: number) {
        this.n = n;
        this.graph = Array.from({ length: n }, () => []);
    }

    addEdge(u: number, v: number, cap: number, cost: number): void {
        this.graph[u].push(new Edge(v, cap, cost, this.graph[v].length));

        this.graph[v].push(new Edge(u, 0, -cost, this.graph[u].length - 1));
    }

    minCostFlow(source: number, sink: number, maxFlow: number): number {
        let totalCost = 0;
        let currentFlow = 0;

        while (currentFlow < maxFlow) {
            const dist = new Array<number>(this.n).fill(MinCostMaxFlow.INF);

            const parentNode = new Array<number>(this.n).fill(-1);

            const parentEdge = new Array<number>(this.n).fill(-1);

            const inQueue = new Array<boolean>(this.n).fill(false);

            const queue: number[] = [];

            queue.push(source);
            dist[source] = 0;
            inQueue[source] = true;

            let head = 0;

            while (head < queue.length) {
                const u = queue[head++];
                inQueue[u] = false;

                for (let i = 0; i < this.graph[u].length; i++) {
                    const e = this.graph[u][i];

                    if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                        dist[e.to] = dist[u] + e.cost;
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;

                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            queue.push(e.to);
                        }
                    }
                }
            }

            if (dist[sink] === MinCostMaxFlow.INF) {
                return -1;
            }

            let pushFlow = maxFlow - currentFlow;

            for (let cur = sink; cur !== source; cur = parentNode[cur]) {
                const e = this.graph[parentNode[cur]][parentEdge[cur]];
                pushFlow = Math.min(pushFlow, e.cap);
            }

            for (let cur = sink; cur !== source; cur = parentNode[cur]) {
                const p = parentNode[cur];
                const idx = parentEdge[cur];

                const e = this.graph[p][idx];

                e.cap -= pushFlow;
                this.graph[cur][e.rev].cap += pushFlow;
            }

            currentFlow += pushFlow;
            totalCost += pushFlow * dist[sink];
        }

        return totalCost;
    }
}

function minMoves(balance: number[]): number {
    let totalBalance = 0;
    let totalDeficit = 0;

    for (const x of balance) {
        totalBalance += x;
        if (x < 0) {
            totalDeficit += -x;
        }
    }

    if (totalBalance < 0) {
        return -1;
    }

    if (totalDeficit === 0) {
        return 0;
    }

    const n = balance.length;

    const source = n;
    const sink = n + 1;

    const mcmf = new MinCostMaxFlow(n + 2);

    for (let i = 0; i < n; i++) {
        if (balance[i] > 0) {
            mcmf.addEdge(source, i, balance[i], 0);
        } else if (balance[i] < 0) {
            mcmf.addEdge(i, sink, -balance[i], 0);
        }

        mcmf.addEdge(i, (i + 1) % n, MinCostMaxFlow.INF, 1);

        mcmf.addEdge(i, (i - 1 + n) % n, MinCostMaxFlow.INF, 1);
    }

    return mcmf.minCostFlow(source, sink, totalDeficit);
}
