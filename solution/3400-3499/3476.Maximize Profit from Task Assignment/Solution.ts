function maxProfit(workers: number[], tasks: number[][]): number {
    const d = new Map();
    for (const [skill, profit] of tasks) {
        if (!d.has(skill)) {
            d.set(skill, new MaxPriorityQueue());
        }
        d.get(skill).enqueue(profit);
    }
    let ans = 0;
    for (const skill of workers) {
        const pq = d.get(skill);
        if (pq) {
            ans += pq.dequeue();
            if (pq.size() === 0) {
                d.delete(skill);
            }
        }
    }
    let mx = 0;
    for (const pq of d.values()) {
        mx = Math.max(mx, pq.front());
    }
    ans += mx;
    return ans;
}
