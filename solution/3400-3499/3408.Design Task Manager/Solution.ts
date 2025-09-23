class TaskManager {
    private d: Map<number, [number, number]>;
    private pq: PriorityQueue<[number, number]>;

    constructor(tasks: number[][]) {
        this.d = new Map();
        this.pq = new PriorityQueue<[number, number]>((a, b) => {
            if (a[0] === b[0]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        for (const task of tasks) {
            this.add(task[0], task[1], task[2]);
        }
    }

    add(userId: number, taskId: number, priority: number): void {
        this.d.set(taskId, [userId, priority]);
        this.pq.enqueue([priority, taskId]);
    }

    edit(taskId: number, newPriority: number): void {
        const e = this.d.get(taskId);
        if (!e) return;
        const userId = e[0];
        this.d.set(taskId, [userId, newPriority]);
        this.pq.enqueue([newPriority, taskId]);
    }

    rmv(taskId: number): void {
        this.d.delete(taskId);
    }

    execTop(): number {
        while (!this.pq.isEmpty()) {
            const [priority, taskId] = this.pq.dequeue();
            const e = this.d.get(taskId);
            if (e && e[1] === priority) {
                this.d.delete(taskId);
                return e[0];
            }
        }
        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * var obj = new TaskManager(tasks)
 * obj.add(userId,taskId,priority)
 * obj.edit(taskId,newPriority)
 * obj.rmv(taskId)
 * var param_4 = obj.execTop()
 */
