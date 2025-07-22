function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    for (let i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    return queries.map(q => _.sortedIndex(nums, q + 1));
}
