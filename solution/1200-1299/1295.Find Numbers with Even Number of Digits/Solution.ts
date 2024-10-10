function findNumbers(nums: number[]): number {
    return nums.filter(x => x.toString().length % 2 === 0).length;
}
