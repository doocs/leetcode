type Obj = Record<any, any>;

function compactObject(obj: Obj): Obj {
    if (!obj || typeof obj !== 'object') {
        return obj;
    }
    if (Array.isArray(obj)) {
        return obj.filter(Boolean).map(compactObject);
    }
    return Object.entries(obj).reduce((acc, [key, value]) => {
        if (value) {
            set(acc, key, compactObject(value));
        }
        return acc;
    }, {} as Obj);
}

function set(target: Obj, key: string, value: any) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}
