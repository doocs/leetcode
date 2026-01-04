function minimumCost(
    s: string,
    t: string,
    flipCost: number,
    swapCost: number,
    crossCost: number,
): number {
    const diff: number[] = [0, 0];
    const n = s.length;

    for (let i = 0; i < n; i++) {
        if (s[i] !== t[i]) {
            diff[s.charCodeAt(i) - 48]++;
        }
    }

    let ans = (diff[0] + diff[1]) * flipCost;

    const mx = Math.max(diff[0], diff[1]);
    const mn = Math.min(diff[0], diff[1]);
    ans = Math.min(ans, mn * swapCost + (mx - mn) * flipCost);

    const avg = (mx + mn) >> 1;
    ans = Math.min(ans, (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost);

    return ans;
}
