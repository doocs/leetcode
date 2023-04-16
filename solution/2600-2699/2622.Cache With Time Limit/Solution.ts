class TimeLimitedCache {
    private cache: Map<number, [value: number, expire: number]> = new Map();

    constructor() {}

    set(key: number, value: number, duration: number): boolean {
        this.removeExpire();
        const ans = this.cache.has(key);
        this.cache.set(key, [value, this.now() + duration]);
        return ans;
    }

    get(key: number): number {
        this.removeExpire();
        return this.cache.get(key)?.[0] ?? -1;
    }

    count(): number {
        this.removeExpire();
        return this.cache.size;
    }

    private now(): number {
        return new Date().getTime();
    }

    private removeExpire(): void {
        const now = this.now();
        for (const [key, [, expire]] of this.cache) {
            if (expire <= now) {
                this.cache.delete(key);
            }
        }
    }
}

/**
 * Your TimeLimitedCache object will be instantiated and called as such:
 * var obj = new TimeLimitedCache()
 * obj.set(1, 42, 1000); // false
 * obj.get(1) // 42
 * obj.count() // 1
 */
