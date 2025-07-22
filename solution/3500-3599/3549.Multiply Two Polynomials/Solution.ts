export function multiply(poly1: number[], poly2: number[]): number[] {
    const n1 = poly1.length,
        n2 = poly2.length;
    if (!n1 || !n2) return [];

    if (Math.min(n1, n2) <= 64) {
        const m = n1 + n2 - 1,
            res = new Array<number>(m).fill(0);
        for (let i = 0; i < n1; ++i) for (let j = 0; j < n2; ++j) res[i + j] += poly1[i] * poly2[j];
        return res.map(v => Math.round(v));
    }

    let n = 1,
        m = n1 + n2 - 1;
    while (n < m) n <<= 1;

    const reA = new Float64Array(n);
    const imA = new Float64Array(n);
    for (let i = 0; i < n1; ++i) reA[i] = poly1[i];

    const reB = new Float64Array(n);
    const imB = new Float64Array(n);
    for (let i = 0; i < n2; ++i) reB[i] = poly2[i];

    fft(reA, imA, false);
    fft(reB, imB, false);

    for (let i = 0; i < n; ++i) {
        const a = reA[i],
            b = imA[i],
            c = reB[i],
            d = imB[i];
        reA[i] = a * c - b * d;
        imA[i] = a * d + b * c;
    }

    fft(reA, imA, true);

    const out = new Array<number>(m);
    for (let i = 0; i < m; ++i) out[i] = Math.round(reA[i]);
    return out;
}

function fft(re: Float64Array, im: Float64Array, invert: boolean): void {
    const n = re.length;

    for (let i = 1, j = 0; i < n; ++i) {
        let bit = n >> 1;
        for (; j & bit; bit >>= 1) j ^= bit;
        j ^= bit;
        if (i < j) {
            [re[i], re[j]] = [re[j], re[i]];
            [im[i], im[j]] = [im[j], im[i]];
        }
    }

    for (let len = 2; len <= n; len <<= 1) {
        const ang = ((2 * Math.PI) / len) * (invert ? -1 : 1);
        const wlenCos = Math.cos(ang),
            wlenSin = Math.sin(ang);

        for (let i = 0; i < n; i += len) {
            let wRe = 1,
                wIm = 0;
            const half = len >> 1;
            for (let j = 0; j < half; ++j) {
                const uRe = re[i + j],
                    uIm = im[i + j];
                const vRe0 = re[i + j + half],
                    vIm0 = im[i + j + half];
                const vRe = vRe0 * wRe - vIm0 * wIm;
                const vIm = vRe0 * wIm + vIm0 * wRe;
                re[i + j] = uRe + vRe;
                im[i + j] = uIm + vIm;
                re[i + j + half] = uRe - vRe;
                im[i + j + half] = uIm - vIm;
                const nextWRe = wRe * wlenCos - wIm * wlenSin;
                wIm = wRe * wlenSin + wIm * wlenCos;
                wRe = nextWRe;
            }
        }
    }

    if (invert) {
        for (let i = 0; i < n; ++i) {
            re[i] /= n;
            im[i] /= n;
        }
    }
}
