function minNumber(nums: number[]): string {
    return nums
        .sort((a, b) => Number(`${a}${b}`) - Number(`${b}${a}`))
        .join('');
}
