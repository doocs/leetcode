function kthDistinct(arr: string[], k: number): string {
    const cnt = new Map<string, number>();

    for (const x of arr) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    for (const [x, c] of cnt) {
        if (c === 1) k--;
        if (!k) return x;
    }

    return '';
}
