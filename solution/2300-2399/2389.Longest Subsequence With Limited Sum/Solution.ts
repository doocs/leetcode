function answerQueries(nums: number[], queries: number[]): number[] {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    return queries.map(querie => {
        let sum = 0;
        for (let i = 0; i < n; i++) {
            sum += nums[i];
            if (sum > querie) {
                return i;
            }
        }
        return n;
    });
}
