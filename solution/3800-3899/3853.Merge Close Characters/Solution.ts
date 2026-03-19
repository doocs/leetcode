function mergeCharacters(s: string, k: number): string {
    const last = new Map<string, number>();
    const ans: string[] = [];
    for (const c of s) {
        const cur = ans.length;
        if (last.has(c) && cur - last.get(c)! <= k) {
            continue;
        }
        ans.push(c);
        last.set(c, cur);
    }
    return ans.join('');
}
