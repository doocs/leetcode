function minOperations(nums, k) {
    const s = new Set([k]);
    for (const x of nums) {
        if (x < k) return -1;
        s.add(x);
    }
    return s.size - 1;
}
