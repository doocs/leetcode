function findItinerary(tickets: string[][]): string[] {
    const g: Record<string, string[]> = {};
    tickets.sort((a, b) => b[1].localeCompare(a[1]));
    for (const [f, t] of tickets) {
        g[f] = g[f] || [];
        g[f].push(t);
    }
    const ans: string[] = [];
    const dfs = (f: string) => {
        while (g[f] && g[f].length) {
            const t = g[f].pop()!;
            dfs(t);
        }
        ans.push(f);
    };
    dfs('JFK');
    return ans.reverse();
}
