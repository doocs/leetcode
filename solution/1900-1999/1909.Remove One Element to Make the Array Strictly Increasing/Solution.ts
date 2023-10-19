function canBeIncreasing(nums: number[]): boolean {
    const check = (p: number) => {
        let prev = undefined;
        for (let j = 0; j < nums.length; j++) {
            if (p != j) {
                if (prev !== undefined && prev >= nums[j]) {
                    return false;
                }
                prev = nums[j];
            }
        }
        return true;
    };
    for (let i = 0; i < nums.length; i++) {
        if (nums[i - 1] >= nums[i]) {
            return check(i - 1) || check(i);
        }
    }
    return true;
}
