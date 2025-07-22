function maximumSubsequenceCount(text: string, pattern: string): number {
    let ans = 0;
    let [x, y] = [0, 0];
    for (const c of text) {
        if (c === pattern[1]) {
            ++y;
            ans += x;
        }
        if (c === pattern[0]) {
            ++x;
        }
    }
    ans += Math.max(x, y);
    return ans;
}
