function longestSubsequence(s: string, k: number): number {
    let numStr = '';
    const n = s.length,
        m = s.split('').reduce((a, c) => a + Number(c), 0);
    for (let i = n - 1; i >= 0; i--) {
        const cur = s.charAt(i).concat(numStr);
        if (parseInt(cur, 2) > k) break;
        numStr = cur;
    }
    return n - m + numStr.split('').reduce((a, c) => a + Number(c), 0);
}
