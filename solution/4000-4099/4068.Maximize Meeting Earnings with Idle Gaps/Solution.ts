function maxEarnings(meetings: number[][]): number {
    const n = meetings.length;
    meetings.sort((a, b) => a[1] - b[1]);

    const preMax = new Array<number>(n + 1).fill(-Infinity);
    let ans = 0;

    for (let i = 0; i < n; i++) {
        const start = meetings[i][0];
        const end = meetings[i][1];
        const revenue = meetings[i][2];

        let val = revenue;
        if (start >= meetings[0][1]) {
            let l = 0;
            let r = i;
            while (l < r) {
                const m = (l + r) >> 1;
                if (meetings[m][1] <= start) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            val += preMax[l] + start;
        }

        ans = Math.max(ans, val);
        preMax[i + 1] = Math.max(preMax[i], val - end);
    }

    return ans;
}
