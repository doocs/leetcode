class BinaryIndexedTree {
    n: number;
    c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function minDeletions(s: string, queries: number[][]): number[] {
    const n = s.length;
    const nums: number[] = Array(n).fill(0);
    const bit = new BinaryIndexedTree(n);

    for (let i = 1; i < n; i++) {
        if (s[i] === s[i - 1]) {
            nums[i] = 1;
            bit.update(i + 1, 1);
        }
    }

    const ans: number[] = [];

    for (const q of queries) {
        if (q[0] === 1) {
            const j = q[1];

            let delta = (nums[j] ^ 1) - nums[j];
            nums[j] ^= 1;
            bit.update(j + 1, delta);

            if (j + 1 < n) {
                delta = (nums[j + 1] ^ 1) - nums[j + 1];
                nums[j + 1] ^= 1;
                bit.update(j + 2, delta);
            }
        } else {
            const l = q[1],
                r = q[2];
            ans.push(bit.query(r + 1) - bit.query(l + 1));
        }
    }

    return ans;
}
