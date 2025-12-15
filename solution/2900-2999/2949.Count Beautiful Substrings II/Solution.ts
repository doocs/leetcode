function beautifulSubstrings(s: string, k: number): number {
    const l = pSqrt(k * 4);
    const n = s.length;
    let sum = n;
    let ans = 0;
    const counter = new Map();
    counter.set(((l - 1) << 17) | sum, 1);
    for (let i = 0; i < n; i++) {
        const char = s[i];
        const bit = (AEIOU_MASK >> (char.charCodeAt(0) - 'a'.charCodeAt(0))) & 1;
        sum += bit * 2 - 1; // 1 -> 1    0 -> -1
        const key = ((i % l) << 17) | sum;
        ans += counter.get(key) || 0; // ans += cnt[(i%k,sum)]++
        counter.set(key, (counter.get(key) ?? 0) + 1);
    }
    return ans;
}
const AEIOU_MASK = 1065233;

function pSqrt(n: number) {
    let res = 1;
    for (let i = 2; i * i <= n; i++) {
        let i2 = i * i;
        while (n % i2 == 0) {
            res *= i;
            n /= i2;
        }
        if (n % i == 0) {
            res *= i;
            n /= i;
        }
    }
    if (n > 1) {
        res *= n;
    }
    return res;
}
