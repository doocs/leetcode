function totalSteps(nums: number[]): number {
    let ans = 0;
    let stack = [];
    for (let num of nums) {
        let max = 0;
        while (stack.length && stack[0][0] <= num) {
            max = Math.max(stack[0][1], max);
            stack.shift();
        }
        if (stack.length) max++;
        ans = Math.max(max, ans);
        stack.unshift([num, max]);
    }
    return ans;
}
