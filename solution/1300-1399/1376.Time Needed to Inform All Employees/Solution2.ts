function numOfMinutes(n: number, headID: number, manager: number[], informTime: number[]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n; ++i) {
        if (manager[i] !== -1) {
            g[manager[i]].push(i);
        }
    }
    const time = Array(n).fill(0);
    const stk: [number, number][] = [[headID, 0]];
    while (stk.length) {
        const [i, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, 1]);
            for (const j of g[i]) {
                stk.push([j, 0]);
            }
        } else {
            let ans = 0;
            for (const j of g[i]) {
                ans = Math.max(ans, time[j] + informTime[i]);
            }
            time[i] = ans;
        }
    }
    return time[headID];
}
