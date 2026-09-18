function maxProduct(s: string): number {
    const n = s.length;
    const m = new Array(n).fill(0);
    const r = new Array(n).fill(0);
    let res = 0;

    // Manacher's algorithm (odd-length palindromes)
    {
        let l = 0, right = -1;
        for (let i = 0; i < n; i++) {
            let k = i > right ? 1 : Math.min(m[l + right - i], right - i + 1);
            while (i - k >= 0 && i + k < n && s[i - k] === s[i + k]) k++;
            m[i] = k--;
            if (i + k > right) {
                l = i - k;
                right = i + k;
            }
        }
    }

    // Compute r[i]: longest palindrome starting at position i
    {
        const q: number[][] = [];
        let front = 0;
        for (let i = n - 1; i >= 0; i--) {
            while (front < q.length && q[front][0] - q[front][1] > i - 1) front++;
            r[i] = 1 + (front >= q.length ? 0 : (q[front][0] - i) * 2);
            q.push([i, m[i]]);
        }
    }

    // Compute running left max & maximize product
    {
        const q1: number[][] = [];
        let front1 = 0;
        let l = 0;
        for (let i = 0; i < n - 1; i++) {
            while (front1 < q1.length && q1[front1][0] + q1[front1][1] < i + 1) front1++;
            l = Math.max(l, 1 + (front1 >= q1.length ? 0 : (i - q1[front1][0]) * 2));
            res = Math.max(res, l * r[i + 1]);
            q1.push([i, m[i]]);
        }
    }

    return res;
}