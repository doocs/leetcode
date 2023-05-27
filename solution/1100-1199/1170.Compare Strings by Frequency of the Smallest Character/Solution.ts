function numSmallerByFrequency(queries: string[], words: string[]): number[] {
    const f = (s: string): number => {
        const cnt = new Array(26).fill(0);
        for (const c of s) {
            cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        for (const x of cnt) {
            if (x) {
                return x;
            }
        }
        return 0;
    };
    const nums: number[] = [];
    for (const w of words) {
        nums.push(f(w));
    }
    nums.sort((a, b) => a - b);
    const ans: number[] = [];
    for (const q of queries) {
        const x = f(q);
        let l = 0,
            r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans.push(nums.length - l);
    }
    return ans;
}
