function divideArray(nums: number[]): boolean {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    return Object.values(cnt).every(x => x % 2 === 0);
}
