function kthDistinct(arr: string[], k: number): string {
    const cnt = new Map<string, number>();
    for (const s of arr) {
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    for (const s of arr) {
        if (cnt.get(s) === 1 && --k === 0) {
            return s;
        }
    }
    return '';
}
