type F = (...args: any[]) => void;

function throttle(fn: F, t: number): F {
    let pre = 0;
    let timeId = null;

    return function (...args) {
        const cur = Date.now();
        if (cur - pre >= t) {
            fn(...args);
            pre = cur;
        } else {
            clearTimeout(timeId);
            timeId = setTimeout(() => {
                fn(...args);
                pre += t;
            }, t - (cur - pre));
        }
    };
}

/**
 * const throttled = throttle(console.log, 100);
 * throttled("log"); // logged immediately.
 * throttled("log"); // logged at t=100ms.
 */
