function findKthSmallest(coins: number[], k: number): number {
    let [l, r] = [1n, BigInt(1e11)];
    const n = coins.length;
    const check = (mx: bigint): boolean => {
        let cnt = 0n;
        for (let i = 1; i < 1 << n; ++i) {
            let v = 1n;
            for (let j = 0; j < n; ++j) {
                if ((i >> j) & 1) {
                    v = lcm(v, BigInt(coins[j]));
                    if (v > mx) {
                        break;
                    }
                }
            }
            const m = bitCount(i);
            if (m & 1) {
                cnt += mx / v;
            } else {
                cnt -= mx / v;
            }
        }
        return cnt >= BigInt(k);
    };
    while (l < r) {
        const mid = (l + r) >> 1n;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }
    return Number(l);
}

function gcd(a: bigint, b: bigint): bigint {
    return b === 0n ? a : gcd(b, a % b);
}

function lcm(a: bigint, b: bigint): bigint {
    return (a * b) / gcd(a, b);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
