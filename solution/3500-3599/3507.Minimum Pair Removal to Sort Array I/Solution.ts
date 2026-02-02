function minimumPairRemoval(nums: number[]): number {
    const arr = nums.slice();
    let ans = 0;
    const isNonDecreasing = (a: number[]): boolean => {
        for (let i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    };
    while (!isNonDecreasing(arr)) {
        let k = 0;
        let s = arr[0] + arr[1];
        for (let i = 1; i < arr.length - 1; ++i) {
            const t = arr[i] + arr[i + 1];
            if (s > t) {
                s = t;
                k = i;
            }
        }
        arr[k] = s;
        arr.splice(k + 1, 1);
        ans++;
    }
    return ans;
}
