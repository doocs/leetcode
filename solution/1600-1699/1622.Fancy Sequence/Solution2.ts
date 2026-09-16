class Fancy {
    private mod = BigInt(1e9 + 7);
    private nums: bigint[] = [];
    private a = 1n;
    private b = 0n;

    append(val: number): void {
        const x = (((BigInt(val) - this.b) % this.mod) + this.mod) % this.mod;
        this.nums.push((x * this.qpow(this.a, 1e9 + 5)) % this.mod);
    }

    addAll(inc: number): void {
        this.b = (this.b + BigInt(inc)) % this.mod;
    }

    multAll(m: number): void {
        this.a = (this.a * BigInt(m)) % this.mod;
        this.b = (this.b * BigInt(m)) % this.mod;
    }

    getIndex(idx: number): number {
        if (idx >= this.nums.length) {
            return -1;
        }
        return Number((this.a * this.nums[idx] + this.b) % this.mod);
    }

    private qpow(x: bigint, n: number): bigint {
        let res = 1n;
        while (n) {
            if (n & 1) {
                res = (res * x) % this.mod;
            }
            x = (x * x) % this.mod;
            n >>= 1;
        }
        return res;
    }
}
