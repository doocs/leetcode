function frequencySort(s: string): string {
    const cnt: Map<string, number> = new Map();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    const cs = Array.from(cnt.keys()).sort((a, b) => cnt.get(b)! - cnt.get(a)!);
    const ans: string[] = [];
    for (const c of cs) {
        ans.push(c.repeat(cnt.get(c)!));
    }
    return ans.join('');
}
