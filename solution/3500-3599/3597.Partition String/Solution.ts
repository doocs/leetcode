function partitionString(s: string): string[] {
    const vis = new Set<string>();
    const ans: string[] = [];
    let t = '';
    for (const c of s) {
        t += c;
        if (!vis.has(t)) {
            vis.add(t);
            ans.push(t);
            t = '';
        }
    }
    return ans;
}
