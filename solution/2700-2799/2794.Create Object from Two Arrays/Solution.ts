function createObject(keysArr: any[], valuesArr: any[]): Record<string, any> {
    const ans: Record<string, any> = {};
    for (let i = 0; i < keysArr.length; ++i) {
        const k = String(keysArr[i]);
        if (Object.prototype.hasOwnProperty.call(ans, k)) {
            continue;
        }
        set(ans, k, valuesArr[i]);
    }
    return ans;
}

function set(target: object, key: string, value: any) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}
