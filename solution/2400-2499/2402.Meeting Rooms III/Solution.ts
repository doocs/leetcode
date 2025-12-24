function mostBooked(n: number, meetings: number[][]): number {
    meetings.sort((a, b) => a[0] - b[0]);

    const idle = new MinPriorityQueue<number>();
    for (let i = 0; i < n; ++i) {
        idle.enqueue(i);
    }
    const busy = new PriorityQueue<[number, number]>((a, b) => {
        if (a[0] === b[0]) {
            return a[1] - b[1];
        }
        return a[0] - b[0];
    });
    const cnt: number[] = new Array(n).fill(0);
    for (const v of meetings) {
        const s = v[0],
            e = v[1];
        while (!busy.isEmpty() && busy.front()[0] <= s) {
            const i = busy.dequeue()[1];
            idle.enqueue(i);
        }
        let i = 0;
        if (!idle.isEmpty()) {
            i = idle.dequeue();
            busy.enqueue([e, i]);
        } else {
            const x = busy.dequeue();
            i = x[1];
            busy.enqueue([x[0] + e - s, i]);
        }
        ++cnt[i];
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (cnt[ans] < cnt[i]) {
            ans = i;
        }
    }
    return ans;
}
