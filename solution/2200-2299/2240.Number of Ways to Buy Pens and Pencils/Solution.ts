function waysToBuyPensPencils(
    total: number,
    cost1: number,
    cost2: number,
): number {
    let ans = 0;
    for (let x = 0; x <= Math.floor(total / cost1); ++x) {
        const y = Math.floor((total - x * cost1) / cost2) + 1;
        ans += y;
    }
    return ans;
}
