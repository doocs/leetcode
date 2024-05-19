class TimeLimitedCache {
    #cache: Map<number, [value: number, expire: number]> = new Map();

    set(key: number, value: number, duration: number): boolean {
        const isExist = this.#cache.has(key)
    
        if (!this.#isExpired(key)) {
          this.#cache.set(key, [value, Date.now() + duration])
        }
    
        return isExist
      }
    
      get(key: number): number {
        if (this.#isExpired(key)) return -1
        const res = this.#cache.get(key)?.[0] ?? -1
        return res
      }
    
      count(): number {
        const xs = Array.from(this.#cache).filter(([key]) => !this.#isExpired(key))
        return xs.length
      }
    
      #isExpired = (key: number) =>
        this.#cache.has(key) && (this.#cache.get(key)?.[1] ?? Number.NEGATIVE_INFINITY) < Date.now()
}

/**
 * Your TimeLimitedCache object will be instantiated and called as such:
 * var obj = new TimeLimitedCache()
 * obj.set(1, 42, 1000); // false
 * obj.get(1) // 42
 * obj.count() // 1
 */
