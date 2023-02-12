function findTheArrayConcVal(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let i = 0;
    let j = n - 1;
    while (i < j) {
        ans += Number(`${nums[i]}${nums[j]}`);
        i++;
        j--;
    }
    if (i === j) {
        ans += nums[i];
    }
    return ans;
}
