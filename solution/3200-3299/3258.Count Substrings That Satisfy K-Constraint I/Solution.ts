function countKConstraintSubstrings(s: string, k: number): number {
    const cnt: [number, number] = [0, 0];
    let [ans, l] = [0, 0];
    for (let r = 0; r < s.length; ++r) {
        cnt[s.charCodeAt(r) - 48]++;
        while (cnt[0] > k && cnt[1] > k) {
            cnt[s.charCodeAt(l++) - 48]--;
        }
        ans += r - l + 1;
    }
    return ans;
}
