function invertObject(obj: Record<any, any>): Record<any, any> {
    const ans: Record<any, any> = {};
    for (const key of Object.keys(obj)) {
        const val = own(obj, key);
        if (Object.prototype.hasOwnProperty.call(ans, val)) {
            const prev = own(ans, val);
            if (Array.isArray(prev)) {
                prev.push(key);
            } else {
                set(ans, val, [prev, key]);
            }
        } else {
            set(ans, val, key);
        }
    }
    return ans;
}

function own(target: object, key: PropertyKey) {
    return Object.getOwnPropertyDescriptor(target, key)?.value;
}

function set(target: object, key: PropertyKey, value: any) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}
