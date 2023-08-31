function sortItems(n: number, m: number, group: number[], beforeItems: number[][]): number[] {
    let idx = m;
    const groupItems: number[][] = new Array(n + m).fill(0).map(() => []);
    const itemDegree: number[] = new Array(n).fill(0);
    const gorupDegree: number[] = new Array(n + m).fill(0);
    const itemGraph: number[][] = new Array(n).fill(0).map(() => []);
    const groupGraph: number[][] = new Array(n + m).fill(0).map(() => []);
    for (let i = 0; i < n; ++i) {
        if (group[i] === -1) {
            group[i] = idx++;
        }
        groupItems[group[i]].push(i);
    }
    for (let i = 0; i < n; ++i) {
        for (const j of beforeItems[i]) {
            if (group[i] === group[j]) {
                ++itemDegree[i];
                itemGraph[j].push(i);
            } else {
                ++gorupDegree[group[i]];
                groupGraph[group[j]].push(group[i]);
            }
        }
    }
    let items = new Array(n + m).fill(0).map((_, i) => i);
    const topoSort = (graph: number[][], degree: number[], items: number[]): number[] => {
        const q: number[] = [];
        for (const i of items) {
            if (degree[i] === 0) {
                q.push(i);
            }
        }
        const ans: number[] = [];
        while (q.length) {
            const i = q.pop()!;
            ans.push(i);
            for (const j of graph[i]) {
                if (--degree[j] === 0) {
                    q.push(j);
                }
            }
        }
        return ans.length === items.length ? ans : [];
    };
    const groupOrder = topoSort(groupGraph, gorupDegree, items);
    if (groupOrder.length === 0) {
        return [];
    }
    const ans: number[] = [];
    for (const gi of groupOrder) {
        items = groupItems[gi];
        const itemOrder = topoSort(itemGraph, itemDegree, items);
        if (itemOrder.length !== items.length) {
            return [];
        }
        ans.push(...itemOrder);
    }
    return ans;
}
