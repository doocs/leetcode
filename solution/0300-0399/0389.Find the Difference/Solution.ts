function findTheDifference(s: string, t: string): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (const c of t) {
        --cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i] < 0) {
            return String.fromCharCode(i + 'a'.charCodeAt(0));
        }
    }
}
