function countSpecialIntegers(nums: number[]): number {
    const g = new Map<number, number[]>();

    for (let i = 0; i < nums.length; i++) {
        if (!g.has(nums[i])) {
            g.set(nums[i], []);
        }
        g.get(nums[i])!.push(i);
    }

    let ans = 0;
    for (const pos of g.values()) {
        if (pos.length === 3 && pos[0] + pos[2] === pos[1] * 2) {
            ans++;
        }
    }
    return ans;
}
