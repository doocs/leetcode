function minCost(colors: string, neededTime: number[]): number {
    let ans = 0;
    const n = neededTime.length;

    for (let i = 0, j = 0; i < n; i = j) {
        j = i;
        let [s, mx] = [0, 0];
        while (j < n && colors[j] === colors[i]) {
            s += neededTime[j];
            mx = Math.max(mx, neededTime[j]);
            ++j;
        }
        if (j - i > 1) {
            ans += s - mx;
        }
    }

    return ans;
}
