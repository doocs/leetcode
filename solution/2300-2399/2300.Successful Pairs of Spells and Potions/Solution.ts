function successfulPairs(
    spells: number[],
    potions: number[],
    success: number,
): number[] {
    potions.sort((a, b) => a - b);
    const m = potions.length;
    const ans: number[] = [];
    for (const v of spells) {
        let left = 0;
        let right = m;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (v * potions[mid] >= success) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        ans.push(m - left);
    }
    return ans;
}
