type Obj = Array<any> | Record<any, any>;

function makeImmutable(obj: Obj): Obj {
    const arrayHandler: ProxyHandler<Array<any>> = {
        set: (_, prop) => {
            throw `Error Modifying Index: ${String(prop)}`;
        },
    };
    const objectHandler: ProxyHandler<Record<any, any>> = {
        set: (_, prop) => {
            throw `Error Modifying: ${String(prop)}`;
        },
    };
    const fnHandler: ProxyHandler<Function> = {
        apply: target => {
            throw `Error Calling Method: ${target.name}`;
        },
    };
    const fn = ['pop', 'push', 'shift', 'unshift', 'splice', 'sort', 'reverse'];
    const dfs = (obj: Obj) => {
        for (const key in obj) {
            if (typeof obj[key] === 'object' && obj[key] !== null) {
                obj[key] = dfs(obj[key]);
            }
        }
        if (Array.isArray(obj)) {
            fn.forEach(f => (obj[f] = new Proxy(obj[f], fnHandler)));
            return new Proxy(obj, arrayHandler);
        }
        return new Proxy(obj, objectHandler);
    };
    return dfs(obj);
}

/**
 * const obj = makeImmutable({x: 5});
 * obj.x = 6; // throws "Error Modifying x"
 */
