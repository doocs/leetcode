function findDuplicate(paths: string[]): string[][] {
    const d = new Map<string, string[]>();
    for (const p of paths) {
        const [root, ...fs] = p.split(' ');
        for (const f of fs) {
            const [name, content] = f.split(/\(|\)/g).filter(Boolean);
            const t = d.get(content) ?? [];
            t.push(root + '/' + name);
            d.set(content, t);
        }
    }
    return [...d.values()].filter(e => e.length > 1);
}
