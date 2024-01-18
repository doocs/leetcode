function maximumCount(nums: number[]): number {
    const count = [0, 0];
    for (const num of nums) {
        if (num < 0) {
            count[0]++;
        } else if (num > 0) {
            count[1]++;
        }
    }
    return Math.max(...count);
}
