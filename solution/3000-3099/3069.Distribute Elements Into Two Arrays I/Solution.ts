function resultArray(nums: number[]): number[] {
    const arr1: number[] = [nums[0]];
    const arr2: number[] = [nums[1]];
    for (const x of nums.slice(2)) {
        if (arr1.at(-1)! > arr2.at(-1)!) {
            arr1.push(x);
        } else {
            arr2.push(x);
        }
    }
    return arr1.concat(arr2);
}
