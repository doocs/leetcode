function minArrivalsToDiscard(arrivals: number[], w: number, m: number): number {
    const cnt = new Map<number, number>();
    const n = arrivals.length;
    const marked = Array<number>(n).fill(0);
    let ans = 0;

    for (let i = 0; i < n; i++) {
        const x = arrivals[i];
        if (i >= w) {
            cnt.set(arrivals[i - w], (cnt.get(arrivals[i - w]) || 0) - marked[i - w]);
        }
        if ((cnt.get(x) || 0) >= m) {
            ans++;
        } else {
            marked[i] = 1;
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    return ans;
}
