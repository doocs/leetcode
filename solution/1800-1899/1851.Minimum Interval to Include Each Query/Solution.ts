function minInterval(intervals: number[][], queries: number[]): number[] {
    const n = intervals.length;
    const m = queries.length;

    intervals.sort((a, b) => a[0] - b[0]);
    const qs = queries.map((x, i) => [x, i] as [number, number]).sort((a, b) => a[0] - b[0]);

    const ans = Array<number>(m).fill(-1);
    const pq = new PriorityQueue<[number, number]>((a, b) =>
        a[0] === b[0] ? a[1] - b[1] : a[0] - b[0],
    );

    let i = 0;
    for (const [x, idx] of qs) {
        while (i < n && intervals[i][0] <= x) {
            const [l, r] = intervals[i];
            pq.enqueue([r - l + 1, r]);
            i++;
        }

        while (pq.size() > 0 && pq.front()![1] < x) {
            pq.dequeue();
        }

        if (pq.size() > 0) {
            ans[idx] = pq.front()![0];
        }
    }

    return ans;
}
