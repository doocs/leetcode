function successfulPairs(
    spells: number[],
    potions: number[],
    success: number,
): number[] {
    const n = spells.length,
        m = potions.length;
    potions.sort((a, b) => a - b);
    let pairs = new Array(n);
    let hashMap = new Map();
    for (let i = 0; i < n; i++) {
        const target = Math.ceil(success / spells[i]);
        let idx = hashMap.get(target);
        if (!idx) {
            idx = searchLeft(potions, 0, m, target);
            hashMap.set(target, idx);
        }
        pairs[i] = m - idx;
    }
    return pairs;
}

function searchLeft(nums, left, right, target) {
    while (left < right) {
        let mid = (left + right) >> 1;
        if (nums[mid] >= target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
