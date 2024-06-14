class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] = Math.max(this.c[x], v);
        }
    }

    query(x: number): number {
        let ans = 0;
        for (; x > 0; x -= x & -x) {
            ans = Math.max(ans, this.c[x]);
        }
        return ans;
    }
}

function minOperations(target: number[], arr: number[]): number {
    const m = target.length;
    const d: Map<number, number> = new Map();
    target.forEach((x, i) => d.set(x, i + 1));
    const nums: number[] = [];
    arr.forEach(x => {
        if (d.has(x)) {
            nums.push(d.get(x)!);
        }
    });
    const tree = new BinaryIndexedTree(m);
    let ans = 0;
    nums.forEach(x => {
        const v = tree.query(x - 1) + 1;
        ans = Math.max(ans, v);
        tree.update(x, v);
    });
    return m - ans;
}
