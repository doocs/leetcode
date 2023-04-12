function isMajorityElement(nums: number[], target: number): boolean {
    const search = (x: number) => {
        let left = 0;
        let right = nums.length;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const left = search(target);
    const right = search(target + 1);
    return right - left > nums.length >> 1;
}
