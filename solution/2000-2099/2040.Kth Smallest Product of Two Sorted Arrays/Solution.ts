function kthSmallestProduct(nums1: number[], nums2: number[], k: number): number {
    const m = nums1.length;
    const n = nums2.length;

    const a = BigInt(Math.max(Math.abs(nums1[0]), Math.abs(nums1[m - 1])));
    const b = BigInt(Math.max(Math.abs(nums2[0]), Math.abs(nums2[n - 1])));

    let l = -a * b;
    let r = a * b;

    const count = (p: bigint): bigint => {
        let cnt = 0n;
        for (const x of nums1) {
            const bx = BigInt(x);
            if (bx > 0n) {
                let l = 0,
                    r = n;
                while (l < r) {
                    const mid = (l + r) >> 1;
                    const prod = bx * BigInt(nums2[mid]);
                    if (prod > p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += BigInt(l);
            } else if (bx < 0n) {
                let l = 0,
                    r = n;
                while (l < r) {
                    const mid = (l + r) >> 1;
                    const prod = bx * BigInt(nums2[mid]);
                    if (prod <= p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += BigInt(n - l);
            } else if (p >= 0n) {
                cnt += BigInt(n);
            }
        }
        return cnt;
    };

    while (l < r) {
        const mid = (l + r) >> 1n;
        if (count(mid) >= BigInt(k)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }

    return Number(l);
}
