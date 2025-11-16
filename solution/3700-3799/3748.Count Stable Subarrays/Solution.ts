function countStableSubarrays(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const seg: number[] = [];
    const s: number[] = [0];

    let l = 0;
    for (let r = 0; r < n; r++) {
        if (r === n - 1 || nums[r] > nums[r + 1]) {
            seg.push(l);
            const k = r - l + 1;
            s.push(s[s.length - 1] + (k * (k + 1)) / 2);
            l = r + 1;
        }
    }

    const ans: number[] = [];
    for (const [left, right] of queries) {
        const i = _.sortedIndex(seg, left + 1);
        const j = _.sortedIndex(seg, right + 1) - 1;

        if (i > j) {
            const k = right - left + 1;
            ans.push((k * (k + 1)) / 2);
        } else {
            const a = seg[i] - left;
            const b = right - seg[j] + 1;
            ans.push((a * (a + 1)) / 2 + s[j] - s[i] + (b * (b + 1)) / 2);
        }
    }

    return ans;
}
