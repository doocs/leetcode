function groupThePeople(groupSizes: number[]): number[][] {
    const n: number = groupSizes.length;
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (let i = 0; i < groupSizes.length; i++) {
        const size: number = groupSizes[i];
        g[size].push(i);
    }
    const ans: number[][] = [];
    for (let i = 1; i <= n; i++) {
        const group: number[] = [];
        for (let j = 0; j < g[i].length; j += i) {
            group.push(...g[i].slice(j, j + i));
            ans.push([...group]);
            group.length = 0;
        }
    }
    return ans;
}
