function numberOfSubstrings(s: string): number {
    let ans = 0,
        l = 0;
    const cnt = [0, 0, 0];

    for (let r = 0; r < s.length; r++) {
        cnt[s.charCodeAt(r) - 97]++;

        while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
            cnt[s.charCodeAt(l) - 97]--;
            l++;
        }

        ans += l;
    }

    return ans;
}
