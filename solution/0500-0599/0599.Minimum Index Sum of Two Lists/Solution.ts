function findRestaurant(list1: string[], list2: string[]): string[] {
    let minI = Infinity;
    const res = [];
    const map = new Map<string, number>(list1.map((s, i) => [s, i]));
    list2.forEach((s, i) => {
        if (map.has(s)) {
            const sumI = i + map.get(s);
            if (sumI <= minI) {
                if (sumI < minI) {
                    minI = sumI;
                    res.length = 0;
                }
                res.push(s);
            }
        }
    });
    return res;
}
