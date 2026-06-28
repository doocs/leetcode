function filterOccupiedIntervals(
    occupiedIntervals: number[][],
    freeStart: number,
    freeEnd: number,
): number[][] {
    occupiedIntervals.sort((a, b) => a[0] - b[0]);

    const busy: number[][] = [occupiedIntervals[0]];

    for (let i = 1; i < occupiedIntervals.length; i++) {
        const cur = occupiedIntervals[i];
        const last = busy[busy.length - 1];

        if (last[1] + 1 < cur[0]) {
            busy.push(cur);
        } else {
            last[1] = Math.max(last[1], cur[1]);
        }
    }

    const ans: number[][] = [];

    for (const [s, e] of busy) {
        if (e < freeStart || s > freeEnd) {
            ans.push([s, e]);
        } else {
            if (s < freeStart) {
                ans.push([s, freeStart - 1]);
            }
            if (e > freeEnd) {
                ans.push([freeEnd + 1, e]);
            }
        }
    }

    return ans;
}
