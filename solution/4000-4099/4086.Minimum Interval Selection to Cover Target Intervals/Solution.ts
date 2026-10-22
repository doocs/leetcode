function minSelection(availableIntervals: number[][], targetIntervals: number[][]): number {
    availableIntervals.sort((a, b) => a[0] - b[0]);
    targetIntervals.sort((a, b) => a[0] - b[0]);
    let ans = 0,
        cur = 0,
        i = 0;
    for (const [l, r] of targetIntervals) {
        while (cur < r) {
            const uncover = Math.max(l, cur);
            let nxt = cur;
            while (i < availableIntervals.length && availableIntervals[i][0] <= uncover) {
                nxt = Math.max(nxt, availableIntervals[i][1]);
                i++;
            }
            if (nxt < uncover || nxt === cur) {
                return -1;
            }
            cur = nxt;
            ans++;
        }
    }
    return ans;
}
