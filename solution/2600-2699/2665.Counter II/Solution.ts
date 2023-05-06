type ReturnObj = {
    increment: () => number;
    decrement: () => number;
    reset: () => number;
};

function createCounter(init: number): ReturnObj {
    let val = init;
    return {
        increment() {
            return ++val;
        },
        decrement() {
            return --val;
        },
        reset() {
            return (val = init);
        },
    };
}

/**
 * const counter = createCounter(5)
 * counter.increment(); // 6
 * counter.reset(); // 5
 * counter.decrement(); // 4
 */
