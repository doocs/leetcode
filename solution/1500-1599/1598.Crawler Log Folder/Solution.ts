function minOperations(logs: string[]): number {
    let depth = 0;
    for (const log of logs) {
        if (log === '../') {
            depth = Math.max(0, depth - 1);
        } else if (log !== './') {
            depth++;
        }
    }
    return depth;
}
