class BrowserHistory {
    private stk1: string[] = [];
    private stk2: string[] = [];

    constructor(homepage: string) {
        this.visit(homepage);
    }

    visit(url: string): void {
        this.stk1.push(url);
        this.stk2 = [];
    }

    back(steps: number): string {
        for (let i = 0; i < steps && this.stk1.length > 1; ++i) {
            this.stk2.push(this.stk1.pop()!);
        }
        return this.stk1.at(-1)!;
    }

    forward(steps: number): string {
        for (let i = 0; i < steps && this.stk2.length; ++i) {
            this.stk1.push(this.stk2.pop()!);
        }
        return this.stk1.at(-1)!;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * var obj = new BrowserHistory(homepage)
 * obj.visit(url)
 * var param_2 = obj.back(steps)
 * var param_3 = obj.forward(steps)
 */
