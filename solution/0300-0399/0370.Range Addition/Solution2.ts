class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function getModifiedArray(length: number, updates: number[][]): number[] {
    const bit = new BinaryIndexedTree(length);
    for (const [l, r, c] of updates) {
        bit.update(l + 1, c);
        bit.update(r + 2, -c);
    }
    return Array.from({ length }, (_, i) => bit.query(i + 1));
}
