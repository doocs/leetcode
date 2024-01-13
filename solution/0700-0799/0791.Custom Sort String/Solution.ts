function customSortString(order: string, s: string): string {
    const toIndex = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = order.length;
    const d = new Array(26).fill(n);
    for (let i = 0; i < n; i++) {
        d[toIndex(order[i])] = i;
    }
    return [...s].sort((a, b) => d[toIndex(a)] - d[toIndex(b)]).join('');
}
