function candy(ratings: number[]): number {
    const n = ratings.length;
    const left = new Array(n).fill(1);
    const right = new Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        if (ratings[i] > ratings[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }
    for (let i = n - 2; i >= 0; --i) {
        if (ratings[i] > ratings[i + 1]) {
            right[i] = right[i + 1] + 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.max(left[i], right[i]);
    }
    return ans;
}
