function minProcessingTime(processorTime: number[], tasks: number[]): number {
    processorTime.sort((a, b) => a - b);
    tasks.sort((a, b) => a - b);
    let [ans, i] = [0, tasks.length - 1];
    for (const t of processorTime) {
        ans = Math.max(ans, t + tasks[i]);
        i -= 4;
    }
    return ans;
}
