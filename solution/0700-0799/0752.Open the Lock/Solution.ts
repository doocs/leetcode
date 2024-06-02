function openLock(deadends: string[], target: string): number {
    const q = [1];
    for (const i of q) {
        if (i > 10) {
            break;
        }
        q.push(i + 1);
    }
}
