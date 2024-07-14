/**
 * Definition for Employee.
 * function Employee(id, importance, subordinates) {
 *     this.id = id;
 *     this.importance = importance;
 *     this.subordinates = subordinates;
 * }
 */

/**
 * @param {Employee[]} employees
 * @param {number} id
 * @return {number}
 */
var GetImportance = function (employees, id) {
    const d = new Map();
    for (const e of employees) {
        d.set(e.id, e);
    }
    const dfs = i => {
        let s = d.get(i).importance;
        for (const j of d.get(i).subordinates) {
            s += dfs(j);
        }
        return s;
    };
    return dfs(id);
};
