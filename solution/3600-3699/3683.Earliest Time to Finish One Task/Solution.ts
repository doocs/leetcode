function earliestTime(tasks: number[][]): number {
    return Math.min(...tasks.map(task => task[0] + task[1]));
}
