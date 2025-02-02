function findRestaurant(list1: string[], list2: string[]): string[] {
    const d = new Map<string, number>(list2.map((s, i) => [s, i]));
    let mi = Infinity;
    const ans: string[] = [];
    list1.forEach((s, i) => {
        if (d.has(s)) {
            const j = d.get(s)!;
            if (i + j < mi) {
                mi = i + j;
                ans.length = 0;
                ans.push(s);
            } else if (i + j === mi) {
                ans.push(s);
            }
        }
    });
    return ans;
}
