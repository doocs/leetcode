function longestBalanced(nums: number[]): number {
    const n = nums.length;

    interface Node {
        l: number;
        r: number;
        mn: number;
        mx: number;
        lazy: number;
    }

    const tr: Node[] = Array.from({ length: (n + 1) * 4 }, () => ({
        l: 0,
        r: 0,
        mn: 0,
        mx: 0,
        lazy: 0,
    }));

    function build(u: number, l: number, r: number) {
        tr[u].l = l;
        tr[u].r = r;
        if (l === r) return;
        const mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) | 1, mid + 1, r);
    }

    function apply(u: number, v: number) {
        tr[u].mn += v;
        tr[u].mx += v;
        tr[u].lazy += v;
    }

    function pushdown(u: number) {
        if (tr[u].lazy !== 0) {
            apply(u << 1, tr[u].lazy);
            apply((u << 1) | 1, tr[u].lazy);
            tr[u].lazy = 0;
        }
    }

    function pushup(u: number) {
        tr[u].mn = Math.min(tr[u << 1].mn, tr[(u << 1) | 1].mn);
        tr[u].mx = Math.max(tr[u << 1].mx, tr[(u << 1) | 1].mx);
    }

    function modify(u: number, l: number, r: number, v: number) {
        if (tr[u].l >= l && tr[u].r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        const mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) modify(u << 1, l, r, v);
        if (r > mid) modify((u << 1) | 1, l, r, v);
        pushup(u);
    }

    function query(u: number, target: number): number {
        if (tr[u].l === tr[u].r) return tr[u].l;
        pushdown(u);
        if (tr[u << 1].mn <= target && target <= tr[u << 1].mx) {
            return query(u << 1, target);
        }
        return query((u << 1) | 1, target);
    }

    build(1, 0, n);

    const last = new Map<number, number>();
    let now = 0,
        ans = 0;

    nums.forEach((x, idx) => {
        const i = idx + 1;
        const det = x & 1 ? 1 : -1;
        if (last.has(x)) {
            modify(1, last.get(x)!, n, -det);
            now -= det;
        }
        last.set(x, i);
        modify(1, i, n, det);
        now += det;
        const pos = query(1, now);
        ans = Math.max(ans, i - pos);
    });

    return ans;
}
