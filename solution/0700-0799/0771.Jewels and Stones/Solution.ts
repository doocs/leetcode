function numJewelsInStones(jewels: string, stones: string): number {
    const s = new Set([...jewels]);
    let ans = 0;
    for (const c of stones) {
        s.has(c) && ans++;
    }
    return ans;
}
