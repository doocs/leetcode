type Obj = Record<any, any>;

function compactObject(obj: Obj): Obj {
    if (Array.isArray(obj)) {
        const temp = [];
        for (const item of obj) {
            if (item) {
                if (typeof item === 'object') temp.push(compactObject(item));
                else temp.push(item);
            }
        }
        return temp;
    }
    for (const [key, value] of Object.entries(obj)) {
        if (!value) delete obj[key];
        else if (typeof value === 'object') obj[key] = compactObject(value);
    }
    return obj;
}
