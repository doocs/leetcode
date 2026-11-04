function objDiff(obj1: any, obj2: any): any {
    if (type(obj1) !== type(obj2)) return [obj1, obj2];
    if (!isObject(obj1)) return obj1 === obj2 ? {} : [obj1, obj2];
    const diff: Record<string, unknown> = {};
    for (const key of Object.keys(obj1)) {
        if (!Object.prototype.hasOwnProperty.call(obj2, key)) {
            continue;
        }
        const subDiff = objDiff(own(obj1, key), own(obj2, key));
        if (Object.keys(subDiff).length) {
            set(diff, key, subDiff);
        }
    }
    return diff;
}

function own(target: object, key: string) {
    return Object.getOwnPropertyDescriptor(target, key)?.value;
}

function set(target: object, key: string, value: unknown) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}

function type(obj: unknown): string {
    return Object.prototype.toString.call(obj).slice(8, -1);
}

function isObject(obj: unknown): obj is Record<string, unknown> {
    return typeof obj === 'object' && obj !== null;
}
