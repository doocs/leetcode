function scoreDifference(nums: number[]): number {
    let ans = 0;
    let k = 1;

    nums.forEach((x, i) => {
        if (x % 2 !== 0) {
            k = -k;
        }
        if (i % 6 === 5) {
            k = -k;
        }
        ans += k * x;
    });

    return ans;
}
