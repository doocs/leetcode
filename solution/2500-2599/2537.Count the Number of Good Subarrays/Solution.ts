function countGood(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let [ans, cur, i] = [0, 0, 0];

    for (const x of nums) {
        const count = cnt.get(x) || 0;
        cur += count;
        cnt.set(x, count + 1);

        while (cur - (cnt.get(nums[i])! - 1) >= k) {
            const countI = cnt.get(nums[i])!;
            cnt.set(nums[i], countI - 1);
            cur -= countI - 1;
            i += 1;
        }

        if (cur >= k) {
            ans += i + 1;
        }
    }

    return ans;
}
