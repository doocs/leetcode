function assignTasks(servers: number[], tasks: number[]): number[] {
    const idle = new PriorityQueue({
        compare: (a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]),
    });
    const busy = new PriorityQueue({
        compare: (a, b) =>
            a[0] === b[0] ? (a[1] === b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0],
    });
    for (let i = 0; i < servers.length; ++i) {
        idle.enqueue([servers[i], i]);
    }
    const ans: number[] = [];
    for (let j = 0; j < tasks.length; ++j) {
        const t = tasks[j];
        while (busy.size() > 0 && busy.front()![0] <= j) {
            const [_, s, i] = busy.dequeue()!;
            idle.enqueue([s, i]);
        }
        if (idle.size() > 0) {
            const [s, i] = idle.dequeue()!;
            busy.enqueue([j + t, s, i]);
            ans.push(i);
        } else {
            const [w, s, i] = busy.dequeue()!;
            busy.enqueue([w + t, s, i]);
            ans.push(i);
        }
    }
    return ans;
}
