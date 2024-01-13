function numTeams(rating: number[]): number {
    let ans = 0;
    const n = rating.length;
    for (let i = 0; i < n; ++i) {
        let l = 0;
        let r = 0;
        for (let j = 0; j < i; ++j) {
            if (rating[j] < rating[i]) {
                ++l;
            }
        }
        for (let j = i + 1; j < n; ++j) {
            if (rating[j] > rating[i]) {
                ++r;
            }
        }
        ans += l * r;
        ans += (i - l) * (n - i - 1 - r);
    }
    return ans;
}
