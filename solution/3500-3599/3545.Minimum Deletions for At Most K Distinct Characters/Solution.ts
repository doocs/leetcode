function minDeletion(s: string, k: number): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    cnt.sort((a, b) => a - b);
    return cnt.slice(0, 26 - k).reduce((a, b) => a + b, 0);
}
