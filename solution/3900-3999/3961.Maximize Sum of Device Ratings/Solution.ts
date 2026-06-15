function maxRatings(units: number[][]): number {
    const n = units[0].length;

    if (n === 1) {
        let ans = 0;
        for (const x of units) {
            ans += x[0];
        }
        return ans;
    }

    let ans = 0;
    let mn = Infinity;
    let mn2 = Infinity;

    for (const x of units) {
        x.sort((a, b) => a - b);
        ans += x[1];
        mn2 = Math.min(mn2, x[1]);
        mn = Math.min(mn, x[0]);
    }

    return ans - (mn2 - mn);
}
