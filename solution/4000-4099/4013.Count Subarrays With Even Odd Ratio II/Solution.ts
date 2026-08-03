class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let sum = 0;
        while (x > 0) {
            sum += this.c[x];
            x -= x & -x;
        }
        return sum;
    }
}

function countRatioSubarrays(nums: number[], a: number, b: number): number {
    const n = nums.length;

    const s = new Array<number>(n + 1).fill(0);

    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + (nums[i] % 2 === 1 ? a : -b);
    }

    const st = [...s].sort((x, y) => x - y);

    const uniq: number[] = [];
    for (const x of st) {
        if (uniq.length === 0 || uniq[uniq.length - 1] !== x) {
            uniq.push(x);
        }
    }

    const bit = new BinaryIndexedTree(uniq.length + 1);

    let ans = 0;

    for (const v of s) {
        const x = _.sortedIndex(uniq, v) + 1;

        ans += bit.query(x);
        bit.update(x, 1);
    }

    return ans;
}
