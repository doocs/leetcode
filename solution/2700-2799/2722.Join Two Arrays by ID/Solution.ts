function join(arr1: any[], arr2: any[]): any[] {
    const d = new Map(arr1.map(x => [x.id, x]));
    arr2.forEach(x => {
        if (d.has(x.id)) {
            d.set(x.id, { ...d.get(x.id), ...x });
        } else {
            d.set(x.id, x);
        }
    });
    return [...d.values()].sort((a, b) => a.id - b.id);
}
