class BinaryIndextedTree {
    n: number;
    c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    update(x: number, val: number): void {
        while (x <= this.n) {
            this.c[x] += val;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function find132pattern(nums: number[]): boolean {
    let s: number[] = [...nums];
    s.sort((a, b) => a - b);
    const n = nums.length;
    const left: number[] = new Array(n + 1).fill(1 << 30);
    let m = 0;
    for (let i = 0; i < n; ++i) {
        left[i + 1] = Math.min(left[i], nums[i]);
        if (i == 0 || s[i] != s[i - 1]) {
            s[m++] = s[i];
        }
    }
    s = s.slice(0, m);
    const tree = new BinaryIndextedTree(m);
    for (let i = n - 1; i >= 0; --i) {
        const x = search(s, nums[i]);
        const y = search(s, left[i]);
        if (x > y && tree.query(x - 1) > tree.query(y)) {
            return true;
        }
        tree.update(x, 1);
    }
    return false;
}

function search(nums: number[], x: number): number {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l + 1;
}
