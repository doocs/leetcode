function countPrimeSetBits(left: number, right: number): number {
    const primes = new Set<number>([2, 3, 5, 7, 11, 13, 17, 19]);
    let ans = 0;

    for (let i = left; i <= right; i++) {
        const bits = bitCount(i);
        if (primes.has(bits)) {
            ans++;
        }
    }

    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
