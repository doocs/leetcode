function maxTotalReward(rewardValues: number[]): number {
    rewardValues.sort((a, b) => a - b);
    rewardValues = [...new Set(rewardValues)];
    let f = 1n;
    for (const x of rewardValues) {
        const mask = (1n << BigInt(x)) - 1n;
        f = f | ((f & mask) << BigInt(x));
    }
    return f.toString(2).length - 1;
}
