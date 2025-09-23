class Router {
    private lim: number;
    private vis: Set<number>;
    private q: [number, number, number][];
    private idx: Map<number, number>;
    private d: Map<number, number[]>;

    constructor(memoryLimit: number) {
        this.lim = memoryLimit;
        this.vis = new Set();
        this.q = [];
        this.idx = new Map();
        this.d = new Map();
    }

    private f(a: number, b: number, c: number): number {
        return ((BigInt(a) << 46n) | (BigInt(b) << 29n) | BigInt(c)) as unknown as number;
    }

    addPacket(source: number, destination: number, timestamp: number): boolean {
        const x = this.f(source, destination, timestamp);
        if (this.vis.has(x)) {
            return false;
        }
        this.vis.add(x);
        if (this.q.length >= this.lim) {
            this.forwardPacket();
        }
        this.q.push([source, destination, timestamp]);
        if (!this.d.has(destination)) {
            this.d.set(destination, []);
        }
        this.d.get(destination)!.push(timestamp);
        return true;
    }

    forwardPacket(): number[] {
        if (this.q.length === 0) {
            return [];
        }
        const [s, d, t] = this.q.shift()!;
        this.vis.delete(this.f(s, d, t));
        this.idx.set(d, (this.idx.get(d) ?? 0) + 1);
        return [s, d, t];
    }

    getCount(destination: number, startTime: number, endTime: number): number {
        const ls = this.d.get(destination) ?? [];
        const k = this.idx.get(destination) ?? 0;
        const i = this.lowerBound(ls, startTime, k);
        const j = this.lowerBound(ls, endTime + 1, k);
        return j - i;
    }

    private lowerBound(arr: number[], target: number, from: number): number {
        let l = from,
            r = arr.length;
        while (l < r) {
            const m = Math.floor((l + r) / 2);
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * var obj = new Router(memoryLimit)
 * var param_1 = obj.addPacket(source,destination,timestamp)
 * var param_2 = obj.forwardPacket()
 * var param_3 = obj.getCount(destination,startTime,endTime)
 */
