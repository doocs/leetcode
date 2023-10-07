function findingUsersActiveMinutes(logs: number[][], k: number): number[] {
    const d: Map<number, Set<number>> = new Map();
    for (const [i, t] of logs) {
        if (!d.has(i)) {
            d.set(i, new Set<number>());
        }
        d.get(i)!.add(t);
    }
    const ans: number[] = Array(k).fill(0);
    for (const [_, ts] of d) {
        ++ans[ts.size - 1];
    }
    return ans;
}
