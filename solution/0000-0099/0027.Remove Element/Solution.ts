function removeElement(nums: number[], val: number): number {
    let k: number = 0;
    for (const x of nums) {
        if (x !== val) {
            nums[k++] = x;
        }
    }
    return k;
}
