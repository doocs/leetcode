/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var countGoodIntegers = function (n, k) {
    const fac = factorial(n);
    let ans = 0;
    const vis = new Set();
    const base = Math.pow(10, Math.floor((n - 1) / 2));

    for (let i = base; i < base * 10; i++) {
        let s = String(i);
        const rev = reverseString(s);
        if (n % 2 === 1) {
            s += rev.substring(1);
        } else {
            s += rev;
        }

        if (parseInt(s, 10) % k !== 0) {
            continue;
        }

        const bs = Array.from(s).sort();
        const t = bs.join('');

        if (vis.has(t)) {
            continue;
        }

        vis.add(t);

        const cnt = Array(10).fill(0);
        for (const c of t) {
            cnt[parseInt(c, 10)]++;
        }

        let res = (n - cnt[0]) * fac[n - 1];
        for (const x of cnt) {
            res /= fac[x];
        }
        ans += res;
    }

    return ans;
};

function factorial(n) {
    const fac = Array(n + 1).fill(1);
    for (let i = 1; i <= n; i++) {
        fac[i] = fac[i - 1] * i;
    }
    return fac;
}

function reverseString(s) {
    return s.split('').reverse().join('');
}
