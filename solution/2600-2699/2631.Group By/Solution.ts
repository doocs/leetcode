declare global {
    interface Array<T> {
        groupBy(fn: (item: T) => string): Record<string, T[]>;
    }
}

Array.prototype.groupBy = function (fn) {
    return this.reduce((acc, item) => {
        const key = fn(item);
        if (Object.prototype.hasOwnProperty.call(acc, key)) {
            own(acc, key).push(item);
        } else {
            set(acc, key, [item]);
        }
        return acc;
    }, {});
};

function own(target: object, key: PropertyKey) {
    return Object.getOwnPropertyDescriptor(target, key)?.value;
}

function set(target: object, key: PropertyKey, value: unknown) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}

/**
 * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
 */
