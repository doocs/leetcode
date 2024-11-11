function primeSubOperation(nums) {
    const p = [];
    const max = Math.max(...nums);

    for (let i = 2; i < max; i++) {
        let isPrime = true;

        for (const x of p) {
            if (i % x === 0) {
                isPrime = false;
                break;
            }
        }

        while (isPrime && p.length <= i) {
            p.push(i);
        }
    }

    for (let i = nums.length - 2; i >= 0; i--) {
        if (nums[i] < nums[i + 1]) continue;

        const [x, next] = [nums[i], nums[i + 1]];
        const prime = p[x - next + 1];

        if (!prime || prime >= x) return false;
        nums[i] -= prime;
    }

    return true;
}
