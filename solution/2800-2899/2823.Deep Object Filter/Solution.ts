function deepFilter(obj: Record<string, any>, fn: Function): Record<string, any> | undefined {
    const set = (target: Record<string, any>, key: string, value: any) => {
        Object.defineProperty(target, key, {
            value,
            writable: true,
            enumerable: true,
            configurable: true,
        });
    };
    const dfs = (data: any): any => {
        if (Array.isArray(data)) {
            const res = data.map(dfs).filter((item: any) => item !== undefined);
            return res.length > 0 ? res : undefined;
        }
        if (typeof data === 'object' && data !== null) {
            const res: Record<string, any> = {};
            for (const [key, value] of Object.entries(data)) {
                const filteredValue = dfs(value);
                if (filteredValue !== undefined) {
                    set(res, key, filteredValue);
                }
            }
            return Object.keys(res).length > 0 ? res : undefined;
        }
        return fn(data) ? data : undefined;
    };

    return dfs(obj);
}
