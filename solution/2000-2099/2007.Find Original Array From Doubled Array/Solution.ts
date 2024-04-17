function findOriginalArray(changed: number[]): number[] {
    changed.sort((a, b) => a - b);
    const cnt: number[] = Array(changed.at(-1)! + 1).fill(0);
    for (const x of changed) {
        ++cnt[x];
    }
    const ans: number[] = [];
    for (const x of changed) {
        if (cnt[x] === 0) {
            continue;
        }
        cnt[x]--;
        const y = x << 1;
        if (y >= cnt.length || cnt[y] <= 0) {
            return [];
        }
        cnt[y]--;
        ans.push(x);
    }
    return ans;
}
