const MOD = 1_000_000_007;

function sumCounts(nums: number[]): number {
    const n = nums.length;

    interface Node {
        l: number;
        r: number;
        sum: number;
        sumSq: number;
        lazy: number;
    }

    const tr: Node[] = Array.from({ length: n << 2 }, () => ({
        l: 0,
        r: 0,
        sum: 0,
        sumSq: 0,
        lazy: 0,
    }));

    const build = (u: number, l: number, r: number) => {
        tr[u].l = l;
        tr[u].r = r;
        if (l === r) {
            return;
        }
        const mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) | 1, mid + 1, r);
    };

    const apply = (u: number, v: number) => {
        const len = tr[u].r - tr[u].l + 1;
        tr[u].sumSq += 2 * v * tr[u].sum + v * v * len;
        tr[u].sum += v * len;
        tr[u].lazy += v;
    };

    const pushdown = (u: number) => {
        if (tr[u].lazy !== 0) {
            apply(u << 1, tr[u].lazy);
            apply((u << 1) | 1, tr[u].lazy);
            tr[u].lazy = 0;
        }
    };

    const pushup = (u: number) => {
        tr[u].sum = tr[u << 1].sum + tr[(u << 1) | 1].sum;
        tr[u].sumSq = tr[u << 1].sumSq + tr[(u << 1) | 1].sumSq;
    };

    const modify = (u: number, l: number, r: number, v: number) => {
        if (tr[u].l >= l && tr[u].r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        const mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r, v);
        }
        if (r > mid) {
            modify((u << 1) | 1, l, r, v);
        }
        pushup(u);
    };

    build(1, 0, n - 1);
    const last = new Map<number, number>();
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const p = last.get(nums[i]) ?? -1;
        modify(1, p + 1, i, 1);
        last.set(nums[i], i);
        ans = (ans + tr[1].sumSq) % MOD;
    }
    return ans;
}
