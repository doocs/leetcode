function makeArrayPositive(nums: number[]): number {
    let l = -1;
    let [ans, preMx, s] = [0, 0, 0];
    for (let r = 0; r < nums.length; r++) {
        const x = nums[r];
        s += x;
        if (r - l > 2 && s <= preMx) {
            ans++;
            l = r;
            preMx = 0;
            s = 0;
        } else if (r - l >= 2) {
            preMx = Math.max(preMx, s - x - nums[r - 1]);
        }
    }
    return ans;
}
