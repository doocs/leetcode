function repairCars(ranks: number[], cars: number): number {
    let left = 0;
    let right = ranks[0] * cars * cars;
    while (left < right) {
        const mid = left + Math.floor((right - left) / 2);
        let cnt = 0;
        for (const r of ranks) {
            cnt += Math.floor(Math.sqrt(mid / r));
        }
        if (cnt >= cars) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
