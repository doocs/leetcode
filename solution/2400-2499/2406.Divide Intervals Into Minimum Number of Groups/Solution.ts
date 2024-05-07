function minGroups(intervals: number[][]): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const q = new PriorityQueue({
        compare: (e1, e2) => {
            return e1 - e2;
        },
    });
    for (const e of intervals) {
        if (!q.isEmpty() && q.front() < e[0]) {
            q.dequeue();
        }
        q.enqueue(e[1]);
    }
    return q.size();
}
