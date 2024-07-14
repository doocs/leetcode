function maximumPrimeDifference(nums: number[]): number {
    const isPrime = (x: number): boolean => {
        if (x < 2) {
            return false;
        }
        for (let i = 2; i <= x / i; i++) {
            if (x % i === 0) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; ; ++i) {
        if (isPrime(nums[i])) {
            for (let j = nums.length - 1; ; --j) {
                if (isPrime(nums[j])) {
                    return j - i;
                }
            }
        }
    }
}
