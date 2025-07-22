function displayTable(orders: string[][]): string[][] {
    const tables: Record<number, Record<string, number>> = {};
    const items: Set<string> = new Set();
    for (const [_, table, foodItem] of orders) {
        const t = +table;
        if (!tables[t]) {
            tables[t] = {};
        }
        if (!tables[t][foodItem]) {
            tables[t][foodItem] = 0;
        }
        tables[t][foodItem]++;
        items.add(foodItem);
    }
    const sortedItems = Array.from(items).sort();
    const ans: string[][] = [];
    const header: string[] = ['Table', ...sortedItems];
    ans.push(header);
    const sortedTableNumbers = Object.keys(tables)
        .map(Number)
        .sort((a, b) => a - b);
    for (const table of sortedTableNumbers) {
        const row: string[] = [table.toString()];
        for (const item of sortedItems) {
            row.push((tables[table][item] || 0).toString());
        }
        ans.push(row);
    }
    return ans;
}
