function findLongestSubarray(array: string[]): string[] {
    const vis = new Map();
    vis.set(0, -1);
    let s = 0,
        mx = 0,
        k = 0;
    for (let i = 0; i < array.length; ++i) {
        s += array[i] >= 'A' ? 1 : -1;
        if (vis.has(s)) {
            const j = vis.get(s);
            if (mx < i - j) {
                mx = i - j;
                k = j + 1;
            }
        } else {
            vis.set(s, i);
        }
    }
    return array.slice(k, k + mx);
}
