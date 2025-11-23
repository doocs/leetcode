function totalWaviness(num1: number, num2: number): number {
    let ans = 0;
    for (let x = num1; x <= num2; x++) {
        ans += f(x);
    }
    return ans;
}

function f(x: number): number {
    const nums: number[] = [];
    while (x > 0) {
        nums.push(x % 10);
        x = Math.floor(x / 10);
    }
    const m = nums.length;
    if (m < 3) return 0;

    let s = 0;
    for (let i = 1; i < m - 1; i++) {
        if (
            (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) ||
            (nums[i] < nums[i - 1] && nums[i] < nums[i + 1])
        ) {
            s++;
        }
    }
    return s;
}
