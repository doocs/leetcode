function containsNearbyDuplicate(nums: number[], k: number): boolean {
    const map = new Map();
    for (let i = 0; i < nums.length; i++) {
        const t = nums[i];
        if (map.has(t) && i - map.get(t) <= k) {
            return true;
        }
        map.set(t, i);
    }
    return false;
}
