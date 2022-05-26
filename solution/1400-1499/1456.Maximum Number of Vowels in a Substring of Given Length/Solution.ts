function maxVowels(s: string, k: number): number {
    const n = s.length;
    let ans = 0;
    let preSum = new Array(n).fill(0);
    let cnt = 0;
    for (let i = 0; i < n && ans != k; i++) {
        let char = s.charAt(i);
        if (['a', 'e', 'i', 'o', 'u'].includes(char)) {
            cnt++;
        }
        preSum[i] = cnt;
        ans = Math.max(i < k ? cnt : preSum[i] - preSum[i - k], ans);
    }
    return ans;
}
