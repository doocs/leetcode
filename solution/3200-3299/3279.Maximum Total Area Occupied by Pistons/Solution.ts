function maxArea(height: number, positions: number[], directions: string): number {
    const delta = new Map<number, number>();
    let diff = 0;
    let res = 0;
    for (let i = 0; i < positions.length; i++) {
        const pos = positions[i];
        const dir = directions[i];
        res += pos;
        if (dir === 'U') {
            diff++;
            delta.set(height - pos, (delta.get(height - pos) ?? 0) - 2);
            delta.set(height * 2 - pos, (delta.get(height * 2 - pos) ?? 0) + 2);
        } else {
            diff--;
            delta.set(pos, (delta.get(pos) ?? 0) + 2);
            delta.set(height + pos, (delta.get(height + pos) ?? 0) - 2);
        }
    }
    let ans = res;
    let pre = 0;
    const keys = [...delta.keys()].sort((a, b) => a - b);
    for (const cur of keys) {
        res += (cur - pre) * diff;
        pre = cur;
        diff += delta.get(cur)!;
        ans = Math.max(ans, res);
    }
    return ans;
}
