function findMaxConsecutiveOnes(nums: number[]): number {
    let [ans, cnt] = [0, 0];
    for (const x of nums) {
        if (x) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 0;
        }
    }
    return ans;
}
