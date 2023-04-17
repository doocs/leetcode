function curry(fn: Function): Function {
    const n = fn.length;
    const allArgs: any[] = [];

    return function curried(...args: any[]) {
        allArgs.push(...args);
        if (allArgs.length < n) {
            return curried;
        }
        return fn(...allArgs);
    };
}

/**
 * function sum(a, b) { return a + b; }
 * const csum = curry(sum);
 * csum(1)(2) // 3
 */
