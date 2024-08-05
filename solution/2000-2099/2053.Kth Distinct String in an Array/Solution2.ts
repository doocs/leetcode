function kthDistinct(arr: string[], k: number): string {
    const distinct = new Set<string>();
    const duplicate = new Set<string>();

    for (const x of arr) {
        if (distinct.has(x)) {
            distinct.delete(x);
            duplicate.add(x);
        } else if (!duplicate.has(x)) distinct.add(x);
    }

    for (const x of distinct) {
        if (--k === 0) return x;
    }

    return '';
}
