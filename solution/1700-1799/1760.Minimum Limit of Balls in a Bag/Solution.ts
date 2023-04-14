function minimumSize(nums: number[], maxOperations: number): number {
    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (const x of nums) {
            cnt += ~~((x - 1) / mid);
        }
        if (cnt <= maxOperations) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
