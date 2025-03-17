class Spreadsheet {
    private d: Map<string, number>;

    constructor(rows: number) {
        this.d = new Map<string, number>();
    }

    setCell(cell: string, value: number): void {
        this.d.set(cell, value);
    }

    resetCell(cell: string): void {
        this.d.delete(cell);
    }

    getValue(formula: string): number {
        let ans = 0;
        const cells = formula.slice(1).split('+');
        for (const cell of cells) {
            ans += isNaN(Number(cell)) ? this.d.get(cell) || 0 : Number(cell);
        }
        return ans;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * var obj = new Spreadsheet(rows)
 * obj.setCell(cell,value)
 * obj.resetCell(cell)
 * var param_3 = obj.getValue(formula)
 */
