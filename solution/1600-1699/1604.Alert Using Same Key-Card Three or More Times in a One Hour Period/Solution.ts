function alertNames(keyName: string[], keyTime: string[]): string[] {
    const d: { [name: string]: number[] } = {};
    for (let i = 0; i < keyName.length; ++i) {
        const name = keyName[i];
        const t = keyTime[i];
        const minutes = +t.slice(0, 2) * 60 + +t.slice(3);
        if (d[name] === undefined) {
            d[name] = [];
        }
        d[name].push(minutes);
    }
    const ans: string[] = [];
    for (const name in d) {
        if (d.hasOwnProperty(name)) {
            const ts = d[name];
            if (ts.length > 2) {
                ts.sort((a, b) => a - b);
                for (let i = 0; i < ts.length - 2; ++i) {
                    if (ts[i + 2] - ts[i] <= 60) {
                        ans.push(name);
                        break;
                    }
                }
            }
        }
    }
    ans.sort();
    return ans;
}
