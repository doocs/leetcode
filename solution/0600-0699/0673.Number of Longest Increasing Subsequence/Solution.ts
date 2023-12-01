class BinaryIndexedTree {
    private n: number;
    private c: number[];
    private d: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
        this.d = Array(n + 1).fill(0);
    }

    update(x: number, v: number, cnt: number): void {
        while (x <= this.n) {
            if (this.c[x] < v) {
                this.c[x] = v;
                this.d[x] = cnt;
            } else if (this.c[x] === v) {
                this.d[x] += cnt;
            }
            x += x & -x;
        }
    }

    query(x: number): [number, number] {
        let v = 0;
        let cnt = 0;
        while (x > 0) {
            if (this.c[x] > v) {
                v = this.c[x];
                cnt = this.d[x];
            } else if (this.c[x] === v) {
                cnt += this.d[x];
            }
            x -= x & -x;
        }
        return [v, cnt];
    }
}

function findNumberOfLIS(nums: number[]): number {
    const arr: number[] = [...new Set(nums)].sort((a, b) => a - b);
    const m: number = arr.length;
    const tree: BinaryIndexedTree = new BinaryIndexedTree(m);
    const search = (x: number): number => {
        let l = 0,
            r = arr.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    };
    for (const x of nums) {
        const i: number = search(x);
        const [v, cnt]: [number, number] = tree.query(i - 1);
        tree.update(i, v + 1, Math.max(cnt, 1));
    }
    return tree.query(m)[1];
}
