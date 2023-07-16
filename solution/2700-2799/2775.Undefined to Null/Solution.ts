function undefinedToNull(obj: Record<any, any>): Record<any, any> {
    for (const key in obj) {
        if (typeof obj[key] === 'object') {
            obj[key] = undefinedToNull(obj[key]);
        }
        if (obj[key] === undefined) {
            obj[key] = null;
        }
    }
    return obj;
}

/**
 * undefinedToNull({"a": undefined, "b": 3}) // {"a": null, "b": 3}
 * undefinedToNull([undefined, undefined]) // [null, null]
 */
