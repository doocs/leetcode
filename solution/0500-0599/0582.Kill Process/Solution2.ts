function killProcess(pid: number[], ppid: number[], kill: number): number[] {
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < pid.length; ++i) {
        if (!g.has(ppid[i])) {
            g.set(ppid[i], []);
        }
        g.get(ppid[i])!.push(pid[i]);
    }
    const ans: number[] = [];
    const stk: number[] = [kill];
    while (stk.length) {
        const i = stk.pop()!;
        ans.push(i);
        const children = g.get(i) ?? [];
        for (let k = children.length - 1; k >= 0; --k) {
            stk.push(children[k]);
        }
    }
    return ans;
}
