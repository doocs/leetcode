class ThroneInheritance {
    private king: string;
    private dead: Set<string> = new Set();
    private g: Map<string, string[]> = new Map();

    constructor(kingName: string) {
        this.king = kingName;
    }

    birth(parentName: string, childName: string): void {
        this.g.set(parentName, this.g.get(parentName) || []);
        this.g.get(parentName)!.push(childName);
    }

    death(name: string): void {
        this.dead.add(name);
    }

    getInheritanceOrder(): string[] {
        const ans: string[] = [];
        const dfs = (x: string) => {
            if (!this.dead.has(x)) {
                ans.push(x);
            }
            for (const y of this.g.get(x) || []) {
                dfs(y);
            }
        };
        dfs(this.king);
        return ans;
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * var obj = new ThroneInheritance(kingName)
 * obj.birth(parentName,childName)
 * obj.death(name)
 * var param_3 = obj.getInheritanceOrder()
 */
