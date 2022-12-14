function numJewelsInStones(jewels: string, stones: string): number {
    const set = new Set([...jewels]);
    let ans = 0;
    for (const c of stones) {
        set.has(c) && ans++;
    }
    return ans;
}
