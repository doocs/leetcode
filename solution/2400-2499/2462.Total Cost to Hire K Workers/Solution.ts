function totalCost(costs: number[], k: number, candidates: number): number {
    const n = costs.length;
    if (candidates * 2 >= n) {
        costs.sort((a, b) => a - b);
        return costs.slice(0, k).reduce((acc, x) => acc + x, 0);
    }
    const pq = new PriorityQueue<number[]>((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    for (let i = 0; i < candidates; ++i) {
        pq.enqueue([costs[i], i]);
        pq.enqueue([costs[n - i - 1], n - i - 1]);
    }
    let [l, r] = [candidates, n - candidates - 1];
    let ans = 0;
    while (k--) {
        const [cost, i] = pq.dequeue()!;
        ans += cost;
        if (l > r) {
            continue;
        }
        if (i < l) {
            pq.enqueue([costs[l], l++]);
        } else {
            pq.enqueue([costs[r], r--]);
        }
    }
    return ans;
}
