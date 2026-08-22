function minOperations(s: string): number {
    const n = s.length;

    let size = 1;
    while (size < 2 * n) {
        size <<= 1;
    }

    const nums: number[] = [];
    for (const c of s) {
        nums.push(c.charCodeAt(0) - 97);
    }

    const cost = Array(26).fill(0);

    for (let t = 0; t < 26; t++) {
        for (let z = 0; z < 26; z++) {
            const d = Math.min(z, 26 - z);
            cost[t] += d * Math.cos((-2 * Math.PI * t * z) / 26);
        }
    }

    const dp = Array(n).fill(0);

    const re = Array(size).fill(0);
    const im = Array(size).fill(0);
    const bre = Array(size).fill(0);
    const bim = Array(size).fill(0);

    function fft(re: number[], im: number[], inv: boolean): void {
        const n = re.length;

        for (let i = 1, j = 0; i < n; i++) {
            let bit = n >> 1;

            while (j & bit) {
                j ^= bit;
                bit >>= 1;
            }

            j ^= bit;

            if (i < j) {
                [re[i], re[j]] = [re[j], re[i]];
                [im[i], im[j]] = [im[j], im[i]];
            }
        }

        for (let len = 2; len <= n; len <<= 1) {
            let ang = (2 * Math.PI) / len;

            if (inv) {
                ang = -ang;
            }

            const wr = Math.cos(ang);
            const wi = Math.sin(ang);
            const half = len >> 1;

            for (let i = 0; i < n; i += len) {
                let cr = 1;
                let ci = 0;

                for (let j = 0; j < half; j++) {
                    const x = i + j;
                    const y = x + half;

                    const tr = re[y] * cr - im[y] * ci;
                    const ti = re[y] * ci + im[y] * cr;

                    const ur = re[x];
                    const ui = im[x];

                    re[x] = ur + tr;
                    im[x] = ui + ti;

                    re[y] = ur - tr;
                    im[y] = ui - ti;

                    const nr = cr * wr - ci * wi;
                    const ni = cr * wi + ci * wr;

                    cr = nr;
                    ci = ni;
                }
            }
        }

        if (inv) {
            for (let i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }

    for (let t = 0; t < 14; t++) {
        const theta = (2 * Math.PI * t) / 26;

        for (let i = 0; i < n; i++) {
            const angle = theta * nums[i];

            re[i] = Math.cos(angle);
            im[i] = Math.sin(angle);
        }

        for (let i = n; i < size; i++) {
            re[i] = 0;
            im[i] = 0;
        }

        fft(re, im, false);

        for (let i = 0; i < size; i++) {
            const j = (size - i) & (size - 1);

            const ar = re[i];
            const ai = im[i];

            const br = re[j];
            const bi = -im[j];

            bre[i] = ar * br - ai * bi;
            bim[i] = -(ar * bi + ai * br);
        }

        fft(bre, bim, false);

        const mult = t === 0 || t === 13 ? 1 : 2;
        const factor = (mult * cost[t]) / size;

        for (let c = 0; c < n; c++) {
            dp[c] += factor * (bre[c] + bre[c + n]);
        }
    }

    let ans = Number.MAX_SAFE_INTEGER;

    for (let k = 0; k < n; k++) {
        const c = (2 * k + n - 1) % n;
        const d = Math.round(dp[c] / 52);

        ans = Math.min(ans, k + d);
    }

    return ans;
}
