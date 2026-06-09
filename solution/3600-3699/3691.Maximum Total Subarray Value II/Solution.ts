class SparseTableRMQ {
    n: number;
    maxLog: number;
    fMax: number[][];
    fMin: number[][];
    lg: number[];

    constructor(data: number[]) {
        this.n = data.length;
        this.maxLog = Math.floor(Math.log2(this.n)) + 2;
        this.fMax = Array.from({ length: this.n }, () => Array(this.maxLog).fill(0));
        this.fMin = Array.from({ length: this.n }, () => Array(this.maxLog).fill(0));
        this.lg = Array(this.n + 1).fill(0);

        for (let i = 2; i <= this.n; i++) {
            this.lg[i] = this.lg[i >> 1] + 1;
        }

        for (let i = 0; i < this.n; i++) {
            this.fMax[i][0] = data[i];
            this.fMin[i][0] = data[i];
        }

        for (let j = 1; j < this.maxLog; j++) {
            for (let i = 0; i <= this.n - (1 << j); i++) {
                this.fMax[i][j] = Math.max(
                    this.fMax[i][j - 1],
                    this.fMax[i + (1 << (j - 1))][j - 1],
                );
                this.fMin[i][j] = Math.min(
                    this.fMin[i][j - 1],
                    this.fMin[i + (1 << (j - 1))][j - 1],
                );
            }
        }
    }

    queryMax(l: number, r: number): number {
        const k = this.lg[r - l + 1];
        return Math.max(this.fMax[l][k], this.fMax[r - (1 << k) + 1][k]);
    }

    queryMin(l: number, r: number): number {
        const k = this.lg[r - l + 1];
        return Math.min(this.fMin[l][k], this.fMin[r - (1 << k) + 1][k]);
    }
}

function maxTotalValue(nums: number[], k: number): number {
    const n = nums.length;
    const st = new SparseTableRMQ(nums);
    const pq = new MaxPriorityQueue<[number, number, number]>({
        compare: (a, b) => b[0] - a[0],
    });

    for (let l = 0; l < n; l++) {
        const val = st.queryMax(l, n - 1) - st.queryMin(l, n - 1);
        pq.enqueue([val, l, n - 1]);
    }

    let ans = 0;
    for (let i = 0; i < k; i++) {
        if (pq.isEmpty()) break;
        const curr = pq.dequeue()!;
        const [val, l, r] = curr;
        ans += val;
        if (r > l) {
            const nextVal = st.queryMax(l, r - 1) - st.queryMin(l, r - 1);
            pq.enqueue([nextVal, l, r - 1]);
        }
    }
    return ans;
}
