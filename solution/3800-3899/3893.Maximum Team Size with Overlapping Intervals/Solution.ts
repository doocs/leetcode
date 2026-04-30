function maximumTeamSize(startTime: number[], endTime: number[]): number {
    const n = startTime.length;
    const intervals: [number, number][] = Array.from({ length: n }, (_, i) => [
        startTime[i],
        endTime[i],
    ]);

    startTime.sort((a, b) => a - b);
    endTime.sort((a, b) => a - b);

    let ans = 0;
    for (const [l, r] of intervals) {
        const i = search(endTime, l - 1);
        const j = search(startTime, r);

        ans = Math.max(ans, j - i);
    }

    return ans;
}

function search(arr: number[], x: number): number {
    let l = 0;
    let r = arr.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (arr[mid] > x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
