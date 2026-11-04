function deepMerge(obj1: any, obj2: any): any {
    const isObj = (obj: any) => obj && typeof obj === 'object';
    const isArr = (obj: any) => Array.isArray(obj);
    if (!isObj(obj1) || !isObj(obj2)) {
        return obj2;
    }
    if (isArr(obj1) !== isArr(obj2)) {
        return obj2;
    }
    for (const key of Object.keys(obj2)) {
        const merged = deepMerge(own(obj1, key), own(obj2, key));
        if (isArr(obj1)) {
            obj1[key] = merged;
        } else {
            set(obj1, key, merged);
        }
    }
    return obj1;
}

function own(target: object, key: string) {
    return Object.getOwnPropertyDescriptor(target, key)?.value;
}

function set(target: object, key: string, value: any) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}

/**
 * let obj1 = {"a": 1, "c": 3}, obj2 = {"a": 2, "b": 2};
 * deepMerge(obj1, obj2); // {"a": 2, "c": 3, "b": 2}
 */
