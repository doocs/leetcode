function canBeEqual(s1: string, s2: string): boolean {
    const cnt: number[][] = Array.from({ length: 2 }, () => Array.from({ length: 26 }, () => 0));
    for (let i = 0; i < s1.length; ++i) {
        ++cnt[i & 1][s1.charCodeAt(i) - 97];
        --cnt[i & 1][s2.charCodeAt(i) - 97];
    }
    return cnt.every(arr => arr.every(x => x === 0));
}
