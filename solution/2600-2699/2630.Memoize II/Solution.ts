type Fn = (...params: any) => any;

function memoize(fn: Fn): Fn {
    const cache: Map<string, any> = new Map();

    return function (...args) {
        const key = args.join('-');
        if (cache.has(key)) {
            return cache.get(key);
        }
        const ans = fn.apply(this, args);
        cache.set(key, ans);
        return ans;
    };
}

/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */
