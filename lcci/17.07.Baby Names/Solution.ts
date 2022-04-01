function trulyMostPopular(names: string[], synonyms: string[]): string[] {
    const map = new Map<string, string>();
    for (const synonym of synonyms) {
        const [k1, k2] = [...synonym]
            .slice(1, synonym.length - 1)
            .join('')
            .split(',');
        const [v1, v2] = [map.get(k1) ?? k1, map.get(k2) ?? k2];
        const min = v1 < v2 ? v1 : v2;
        const max = v1 < v2 ? v2 : v1;
        map.set(k1, min);
        map.set(k2, min);
        for (const [k, v] of map.entries()) {
            if (v === max) {
                map.set(k, min);
            }
        }
    }

    const keyCount = new Map<string, number>();
    for (const name of names) {
        const num = name.match(/\d+/)[0];
        const k = name.split('(')[0];
        const key = map.get(k) ?? k;
        keyCount.set(key, (keyCount.get(key) ?? 0) + Number(num));
    }
    return [...keyCount.entries()].map(([k, v]) => `${k}(${v})`);
}
