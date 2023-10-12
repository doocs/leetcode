function maxSubarrays(nums: number[]): number {
    let [ans, score] = [1, -1];
    for (const num of nums) {
        score &= num;
        if (score === 0) {
            --score;
            ++ans;
        }
    }
    return ans == 1 ? 1 : ans - 1;
}
