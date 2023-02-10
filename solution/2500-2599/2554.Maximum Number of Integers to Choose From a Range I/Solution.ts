function maxCount(banned: number[], n: number, maxSum: number): number {
    const set = new Set(banned);
    let sum = 0;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        if (i + sum > maxSum) {
            break;
        }
        if (set.has(i)) {
            continue;
        }
        sum += i;
        ans++;
    }
    return ans;
}
