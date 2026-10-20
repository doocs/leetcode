function sortableIntegers(nums: number[]): number {
    const n = nums.length;
    const prefMax = new Array<number>(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
    }
    const suffMin = new Array<number>(n + 1).fill(1e9);
    for (let i = n - 1; i >= 0; i--) {
        suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
    }
    const prefDrop = new Array<number>(n).fill(0);
    for (let i = 1; i < n; i++) {
        prefDrop[i] = prefDrop[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
    }

    const check = (k: number): boolean => {
        for (let start = 0; start < n; start += k) {
            const end = start + k - 1;
            let drops = prefDrop[end] - prefDrop[start];
            if (nums[end] > nums[start]) {
                drops++;
            }
            if (drops > 1 || prefMax[start] > suffMin[start]) {
                return false;
            }
        }
        return true;
    };

    let ans = 0;
    for (let i = 1; i <= Math.floor(n / i); i++) {
        if (n % i === 0) {
            const other = n / i;
            if (check(i)) {
                ans += i;
            }
            if (i !== other && check(other)) {
                ans += other;
            }
        }
    }
    return ans;
}
