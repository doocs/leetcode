function countSubarrays(nums: number[], k: number, m: number): number {
    const f = (lim: number): number => {
        const cnt = new Map<number, number>();
        let ans = 0;
        let l = 0;
        let t = 0;

        for (const x of nums) {
            cnt.set(x, (cnt.get(x) ?? 0) + 1);
            if (cnt.get(x) === m) {
                t++;
            }

            while (cnt.size >= lim && t >= k) {
                const y = nums[l++];
                cnt.set(y, cnt.get(y)! - 1);

                if (cnt.get(y) === m - 1) {
                    t--;
                }

                if (cnt.get(y) === 0) {
                    cnt.delete(y);
                }
            }

            ans += l;
        }

        return ans;
    };

    return f(k) - f(k + 1);
}
