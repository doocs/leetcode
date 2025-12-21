function minOperations(nums: number[]): number {
    const st = new Set<number>();
    for (let i = nums.length - 1; i >= 0; i--) {
        if (st.has(nums[i])) {
            return Math.floor(i / 3) + 1;
        }
        st.add(nums[i]);
    }
    return 0;
}
