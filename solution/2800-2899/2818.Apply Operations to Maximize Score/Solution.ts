function maximumScore(nums: number[], k: number): number {
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    const arr: number[][] = Array(n)
        .fill(0)
        .map(() => Array(3).fill(0));
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    for (let i = 0; i < n; ++i) {
        arr[i] = [i, primeFactors(nums[i]), nums[i]];
    }
    const stk: number[] = [];
    for (const [i, f, _] of arr) {
        while (stk.length && arr[stk.at(-1)!][1] < f) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk.at(-1)!;
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        const f = arr[i][1];
        while (stk.length && arr[stk.at(-1)!][1] <= f) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1)!;
        }
        stk.push(i);
    }
    arr.sort((a, b) => b[2] - a[2]);
    let ans = 1n;
    for (const [i, _, x] of arr) {
        const l = left[i];
        const r = right[i];
        const cnt = (i - l) * (r - i);
        if (cnt <= k) {
            ans = (ans * qmi(BigInt(x), cnt, BigInt(mod))) % BigInt(mod);
            k -= cnt;
        } else {
            ans = (ans * qmi(BigInt(x), k, BigInt(mod))) % BigInt(mod);
            break;
        }
    }
    return Number(ans);
}

function primeFactors(n: number): number {
    let i = 2;
    const s: Set<number> = new Set();
    while (i * i <= n) {
        while (n % i === 0) {
            s.add(i);
            n = Math.floor(n / i);
        }
        ++i;
    }
    if (n > 1) {
        s.add(n);
    }
    return s.size;
}

function qmi(a: bigint, k: number, p: bigint): bigint {
    let res = 1n;
    while (k) {
        if ((k & 1) === 1) {
            res = (res * a) % p;
        }
        k >>= 1;
        a = (a * a) % p;
    }
    return res;
}
