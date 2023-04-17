type F = (x: number) => number;

function compose(functions: F[]): F {
    return function (x) {
        return functions.reduceRight((acc, fn) => fn(acc), x);
    };
}

/**
 * const fn = compose([x => x + 1, x => 2 * x])
 * fn(4) // 9
 */
