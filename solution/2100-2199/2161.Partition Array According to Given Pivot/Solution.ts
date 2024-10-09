function pivotArray(nums: number[], pivot: number): number[] {
    const ans: number[] = [];
    for (const x of nums) {
        if (x < pivot) {
            ans.push(x);
        }
    }
    for (const x of nums) {
        if (x === pivot) {
            ans.push(x);
        }
    }
    for (const x of nums) {
        if (x > pivot) {
            ans.push(x);
        }
    }
    return ans;
}
