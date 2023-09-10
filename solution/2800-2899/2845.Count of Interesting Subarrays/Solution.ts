function countInterestingSubarrays(nums: number[], modulo: number, k: number): number {
    const arr: number[] = [];
    for (const x of nums) {
        arr.push(x % modulo === k ? 1 : 0);
    }
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    let ans = 0;
    let s = 0;
    for (const x of arr) {
        s += x;
        ans += cnt.get((s - k + modulo) % modulo) || 0;
        cnt.set(s % modulo, (cnt.get(s % modulo) || 0) + 1);
    }
    return ans;
}
