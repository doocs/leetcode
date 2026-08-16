function minOperations(s: string): number {
    const n = s.length;
    let ans = Infinity;

    for (let k = 0; k < n; k++) {
        let t = k;
        let i = 0;
        let j = n - 1;

        while (i < j) {
            const x = s.charCodeAt((i + k) % n) - 97;
            const y = s.charCodeAt((j + k) % n) - 97;

            const d = Math.abs(x - y);
            t += Math.min(d, 26 - d);

            i++;
            j--;
        }

        ans = Math.min(ans, t);
    }

    return ans;
}
