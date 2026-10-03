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
        const stk: string[] = [this.king];
        while (stk.length) {
            const x = stk.pop()!;
            if (!this.dead.has(x)) {
                ans.push(x);
            }
            const children = this.g.get(x) || [];
            for (let i = children.length - 1; i >= 0; --i) {
                stk.push(children[i]);
            }
        }
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
