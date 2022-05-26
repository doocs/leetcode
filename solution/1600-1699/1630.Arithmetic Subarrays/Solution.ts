function checkArithmeticSubarrays(
    nums: number[],
    l: number[],
    r: number[],
): boolean[] {
    const m = l.length;
    const res = new Array(m).fill(true);
    for (let i = 0; i < m; i++) {
        const arr = nums.slice(l[i], r[i] + 1).sort((a, b) => b - a);
        for (let j = 2; j < arr.length; j++) {
            if (arr[j - 2] - arr[j - 1] !== arr[j - 1] - arr[j]) {
                res[i] = false;
                break;
            }
        }
    }
    return res;
}
