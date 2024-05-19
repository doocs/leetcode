function maxUpgrades(
    count: number[],
    upgrade: number[],
    sell: number[],
    money: number[],
): number[] {
    const n = count.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        const x = ((count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i])) | 0;
        ans.push(Math.min(x, count[i]));
    }
    return ans;
}
