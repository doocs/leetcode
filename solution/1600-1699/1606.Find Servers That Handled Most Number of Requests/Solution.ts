function busiestServers(k: number, arrival: number[], load: number[]): number[] {
    const free = new PriorityQueue<number>((a, b) => a - b);
    for (let i = 0; i < k; ++i) {
        free.enqueue(i);
    }
    const busy = new PriorityQueue<[number, number]>((a, b) => a[0] - b[0]);
    const cnt: number[] = Array(k).fill(0);
    for (let i = 0; i < arrival.length; ++i) {
        const start = arrival[i];
        const end = start + load[i];
        while (!busy.isEmpty() && busy.front()![0] <= start) {
            const server = busy.dequeue()![1];
            free.enqueue(i + ((server - (i % k) + k) % k));
        }
        if (free.isEmpty()) {
            continue;
        }
        const server = free.dequeue()! % k;
        ++cnt[server];
        busy.enqueue([end, server]);
    }
    let mx = 0;
    for (const v of cnt) {
        mx = Math.max(mx, v);
    }
    const ans: number[] = [];
    for (let i = 0; i < k; ++i) {
        if (cnt[i] === mx) {
            ans.push(i);
        }
    }
    return ans;
}
