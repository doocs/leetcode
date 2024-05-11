function minimumTime(time: number[], totalTrips: number): number {
    let left = 1n;
    let right = BigInt(Math.min(...time)) * BigInt(totalTrips);
    while (left < right) {
        const mid = (left + right) >> 1n;
        const cnt = time.reduce((acc, v) => acc + mid / BigInt(v), 0n);
        if (cnt >= BigInt(totalTrips)) {
            right = mid;
        } else {
            left = mid + 1n;
        }
    }
    return Number(left);
}
