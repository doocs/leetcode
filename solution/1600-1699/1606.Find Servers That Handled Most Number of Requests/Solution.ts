type Job = [at: number, server: number];
function busiestServers(k: number, arrival: number[], load: number[]): number[] {
    const free = new PriorityQueue<number>((a, b) => a - b, [...Array(k).keys()]);
    const busy = new PriorityQueue<Job>((a, b) => a[0] - b[0]);
    const handled: number[] = new Array(k).fill(0);
    for (const [i, t] of arrival.entries()) {
        while (!busy.isEmpty() && busy.front()![0] <= t) {
            const [, x] = busy.dequeue()!;
            free.enqueue(i + (x - i % k + k) % k);
        }
        if (free.isEmpty()) continue;
        const x = free.dequeue()! % k;
        busy.enqueue([t + load[i], x]);
        handled[x]++;
    }
    const most = handled.reduce((a, b) => Math.max(a, b));
    return [...handled.keys()].filter(x => handled[x] === most);
}