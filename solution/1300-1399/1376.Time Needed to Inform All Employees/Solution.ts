function numOfMinutes(
    n: number,
    headID: number,
    manager: number[],
    informTime: number[],
): number {
    if (n === 1) {
        return 0;
    }
    let res = 0;
    const time = new Array(n).fill(0);
    time[headID] = -1;
    const dfs = (i: number) => {
        const aim = manager[i];
        if (time[aim] === -1) {
            return informTime[aim];
        }
        if (time[aim] === 0) {
            time[aim] = dfs(aim);
        }
        return time[aim] + informTime[aim];
    };
    for (let i = 0; i < n; i++) {
        if (time[i] === 0) {
            time[i] = dfs(i);
        }
        res = Math.max(res, time[i]);
    }
    return res;
}
