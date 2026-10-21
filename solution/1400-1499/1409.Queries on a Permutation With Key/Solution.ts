function processQueries(queries: number[], m: number): number[] {
    const p = Array.from({ length: m }, (_, i) => i + 1);
    const ans: number[] = [];
    for (const v of queries) {
        const j = p.indexOf(v);
        ans.push(j);
        p.splice(j, 1);
        p.unshift(v);
    }
    return ans;
}
