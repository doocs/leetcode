function prefixConnected(words: string[], k: number): number {
    const cnt = new Map<string, number>();

    for (const w of words) {
        if (w.length >= k) {
            const key = w.substring(0, k);
            cnt.set(key, (cnt.get(key) ?? 0) + 1);
        }
    }

    let ans = 0;
    for (const v of cnt.values()) {
        if (v > 1) {
            ans++;
        }
    }

    return ans;
}
