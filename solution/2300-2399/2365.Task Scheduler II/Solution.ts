function taskSchedulerII(tasks: number[], space: number): number {
    const day = new Map<number, number>();
    let ans = 0;
    for (const task of tasks) {
        ++ans;
        ans = Math.max(ans, day.get(task) ?? 0);
        day.set(task, ans + space + 1);
    }
    return ans;
}
