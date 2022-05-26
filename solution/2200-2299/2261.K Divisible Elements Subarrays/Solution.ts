function countDistinct(nums: number[], k: number, p: number): number {
    const n = nums.length;
    const numSet = new Set(nums);
    const verfiedSet = new Set<number>();
    for (let i of numSet) {
        if (i % p != 0) continue;
        verfiedSet.add(i);
    }
    let ans = new Set<string>();
    for (let i = 0; i < n; i++) {
        let sub = [];
        for (let j = i, cnt = 0; j < n; j++) {
            const num = nums[j];
            if (verfiedSet.has(num)) cnt++;
            if (cnt > k) break;
            sub.push(num);
            const str = sub.join(',');
            ans.add(str);
        }
    }
    return ans.size;
}
