/**
 * Definition for Employee.
 * class Employee {
 *     id: number
 *     importance: number
 *     subordinates: number[]
 *     constructor(id: number, importance: number, subordinates: number[]) {
 *         this.id = (id === undefined) ? 0 : id;
 *         this.importance = (importance === undefined) ? 0 : importance;
 *         this.subordinates = (subordinates === undefined) ? [] : subordinates;
 *     }
 * }
 */

function getImportance(employees: Employee[], id: number): number {
    const d = new Map<number, Employee>();
    for (const e of employees) {
        d.set(e.id, e);
    }
    const dfs = (i: number): number => {
        let s = d.get(i)!.importance;
        for (const j of d.get(i)!.subordinates) {
            s += dfs(j);
        }
        return s;
    };
    return dfs(id);
}
