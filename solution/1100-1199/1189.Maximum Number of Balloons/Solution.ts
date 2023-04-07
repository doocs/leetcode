function maxNumberOfBalloons(text: string): number {
    const cnt = new Array(26).fill(0);
    for (const c of text) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    return Math.min(cnt[0], cnt[1], cnt[11] >> 1, cnt[14] >> 1, cnt[13]);
}
