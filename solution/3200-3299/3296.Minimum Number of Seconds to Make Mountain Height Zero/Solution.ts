function minNumberOfSeconds(mountainHeight: number, workerTimes: number[]): number {
    const check = (t: bigint): boolean => {
        let h = BigInt(0);
        for (const wt of workerTimes) {
            h += BigInt(Math.floor(Math.sqrt((Number(t) * 2.0) / wt + 0.25) - 0.5));
        }
        return h >= BigInt(mountainHeight);
    };

    let l = BigInt(1);
    let r = BigInt(1e16);

    while (l < r) {
        const mid = (l + r) >> BigInt(1);
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }

    return Number(l);
}
