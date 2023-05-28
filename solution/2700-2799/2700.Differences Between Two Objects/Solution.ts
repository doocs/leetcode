function objDiff(obj1: any, obj2: any): any {
    if (type(obj1) !== type(obj2)) return [obj1, obj2];
    if (!isObject(obj1)) return obj1 === obj2 ? {} : [obj1, obj2];
    const diff: Record<string, unknown> = {};
    const sameKeys = Object.keys(obj1).filter(key => key in obj2);
    sameKeys.forEach(key => {
        const subDiff = objDiff(obj1[key], obj2[key]);
        if (Object.keys(subDiff).length) diff[key] = subDiff;
    });
    return diff;
}

function type(obj: unknown): string {
    return Object.prototype.toString.call(obj).slice(8, -1);
}

function isObject(obj: unknown): obj is Record<string, unknown> {
    return typeof obj === 'object' && obj !== null;
}
