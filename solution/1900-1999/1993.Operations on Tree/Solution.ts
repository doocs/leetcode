class LockingTree {
    private locked: number[];
    private parent: number[];
    private children: number[][];

    constructor(parent: number[]) {
        const n = parent.length;
        this.locked = Array(n).fill(-1);
        this.parent = parent;
        this.children = Array(n)
            .fill(0)
            .map(() => []);
        for (let i = 1; i < n; i++) {
            this.children[parent[i]].push(i);
        }
    }

    lock(num: number, user: number): boolean {
        if (this.locked[num] === -1) {
            this.locked[num] = user;
            return true;
        }
        return false;
    }

    unlock(num: number, user: number): boolean {
        if (this.locked[num] === user) {
            this.locked[num] = -1;
            return true;
        }
        return false;
    }

    upgrade(num: number, user: number): boolean {
        let x = num;
        for (; x !== -1; x = this.parent[x]) {
            if (this.locked[x] !== -1) {
                return false;
            }
        }
        let find = false;
        const dfs = (x: number) => {
            for (const y of this.children[x]) {
                if (this.locked[y] !== -1) {
                    this.locked[y] = -1;
                    find = true;
                }
                dfs(y);
            }
        };
        dfs(num);
        if (!find) {
            return false;
        }
        this.locked[num] = user;
        return true;
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * var obj = new LockingTree(parent)
 * var param_1 = obj.lock(num,user)
 * var param_2 = obj.unlock(num,user)
 * var param_3 = obj.upgrade(num,user)
 */
