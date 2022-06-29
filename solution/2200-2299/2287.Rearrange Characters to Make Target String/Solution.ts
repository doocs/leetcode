function rearrangeCharacters(s: string, target: string): number {
    let cnt1 = new Array(128).fill(0),
        cnt2 = new Array(128).fill(0);
    for (let i of target) {
        cnt1[i.charCodeAt(0)]++;
    }
    for (let i of s) {
        cnt2[i.charCodeAt(0)]++;
    }
    let ans = Infinity;
    for (let i = 0; i < 128; i++) {
        if (cnt1[i] === 0) continue;
        ans = Math.min(ans, Math.floor(cnt2[i] / cnt1[i]));
    }
    return ans === Infinity ? 0 : ans;
}
