function countKConstraintSubstrings(s: string, k: number): number {
    const cnt: [number, number] = [0, 0];
    let [ans, l] = [0, 0];
    for (let r = 0; r < s.length; ++r) {
        cnt[+s[r]]++;
        while (cnt[0] > k && cnt[1] > k) {
            cnt[+s[l++]]--;
        }
        ans += r - l + 1;
    }
    return ans;
}
