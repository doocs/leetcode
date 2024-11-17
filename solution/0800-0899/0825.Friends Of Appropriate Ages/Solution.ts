function numFriendRequests(ages: number[]): number {
    const m = 121;
    const cnt = Array(m).fill(0);
    for (const x of ages) {
        cnt[x]++;
    }

    let ans = 0;
    for (let ax = 0; ax < m; ax++) {
        for (let ay = 0; ay < m; ay++) {
            if (ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100)) {
                continue;
            }
            ans += cnt[ax] * (cnt[ay] - (ax === ay ? 1 : 0));
        }
    }

    return ans;
}
