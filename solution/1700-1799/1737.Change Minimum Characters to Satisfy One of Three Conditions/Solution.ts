function minCharacters(a: string, b: string): number {
    const m = a.length,
        n = b.length;
    let count1 = new Array(26).fill(0);
    let count2 = new Array(26).fill(0);
    const base = 'a'.charCodeAt(0);

    for (let char of a) {
        count1[char.charCodeAt(0) - base]++;
    }
    for (let char of b) {
        count2[char.charCodeAt(0) - base]++;
    }

    let pre1 = 0,
        pre2 = 0;
    let ans = m + n;
    for (let i = 0; i < 25; i++) {
        pre1 += count1[i];
        pre2 += count2[i];
        // case1， case2， case3
        ans = Math.min(
            ans,
            m - pre1 + pre2,
            pre1 + n - pre2,
            m + n - count1[i] - count2[i],
        );
    }
    ans = Math.min(ans, m + n - count1[25] - count2[25]);

    return ans;
}
