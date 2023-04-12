class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    public update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] += v;
            x += x & -x;
        }
    }

    public query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function numTeams(rating: number[]): number {
    let nums = [...rating];
    nums.sort((a, b) => a - b);
    const n = rating.length;
    let m = 0;
    for (let i = 0; i < n; ++i) {
        if (i === 0 || nums[i] !== nums[i - 1]) {
            nums[m++] = nums[i];
        }
    }
    nums = nums.slice(0, m);
    const search = (x: number): number => {
        let l = 0;
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    };
    let ans = 0;
    const tree1 = new BinaryIndexedTree(m);
    const tree2 = new BinaryIndexedTree(m);
    for (const x of rating) {
        tree2.update(search(x), 1);
    }
    for (let i = 0; i < n; ++i) {
        const x = search(rating[i]);
        tree1.update(x, 1);
        tree2.update(x, -1);
        const l = tree1.query(x - 1);
        const r = n - i - 1 - tree2.query(x);
        ans += l * r;
        ans += (i - l) * (n - i - 1 - r);
    }
    return ans;
}
