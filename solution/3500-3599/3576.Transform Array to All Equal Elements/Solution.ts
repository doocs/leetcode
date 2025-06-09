function canMakeEqual(nums: number[], k: number): boolean {
    function check(target: number, k: number): boolean {
        let [cnt, sign] = [0, 1];
        for (let i = 0; i < nums.length - 1; i++) {
            const x = nums[i] * sign;
            if (x === target) {
                sign = 1;
            } else {
                sign = -1;
                cnt++;
            }
        }
        return cnt <= k && nums[nums.length - 1] * sign === target;
    }

    return check(nums[0], k) || check(-nums[0], k);
}
