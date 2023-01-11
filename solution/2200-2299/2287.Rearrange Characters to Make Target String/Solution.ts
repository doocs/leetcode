function rearrangeCharacters(s: string, target: string): number {
    const idx = (s: string) => s.charCodeAt(0) - 97;
    const cnt1 = new Array(26).fill(0);
    const cnt2 = new Array(26).fill(0);
    for (const c of s) {
        ++cnt1[idx(c)];
    }
    for (const c of target) {
        ++cnt2[idx(c)];
    }
    let ans = 100;
    for (let i = 0; i < 26; ++i) {
        if (cnt2[i]) {
            ans = Math.min(ans, Math.floor(cnt1[i] / cnt2[i]));
        }
    }
    return ans;
}
