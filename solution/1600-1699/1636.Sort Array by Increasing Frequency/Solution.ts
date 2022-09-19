function frequencySort(nums: number[]): number[] {
    const map = new Map<number, number>();
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }
    return nums.sort((a, b) => map.get(a) - map.get(b) || b - a);
}
