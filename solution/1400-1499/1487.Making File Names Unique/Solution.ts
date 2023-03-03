function getFolderNames(names: string[]): string[] {
    let d: Map<string, number> = new Map();
    for (let i = 0; i < names.length; ++i) {
        if (d.has(names[i])) {
            let k: number = d.get(names[i]) || 0;
            while (d.has(names[i] + '(' + k + ')')) {
                ++k;
            }
            d.set(names[i], k);
            names[i] += '(' + k + ')';
        }
        d.set(names[i], 1);
    }
    return names;
}
