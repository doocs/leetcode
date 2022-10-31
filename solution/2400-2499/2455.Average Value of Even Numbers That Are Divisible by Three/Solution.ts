function averageValue(nums: number[]): number {
    let sum = 0;
    let n = 0;
    for (const num of nums) {
        if (num % 6 === 0) {
            sum += num;
            n++;
        }
    }

    if (n === 0) {
        return 0;
    }
    return Math.floor(sum / n);
}
