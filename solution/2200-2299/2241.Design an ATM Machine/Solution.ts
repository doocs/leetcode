class ATM {
    private cnt: number[];
    private d: number[];

    constructor() {
        this.cnt = [0, 0, 0, 0, 0];
        this.d = [20, 50, 100, 200, 500];
    }

    deposit(banknotesCount: number[]): void {
        for (let i = 0; i < banknotesCount.length; i++) {
            this.cnt[i] += banknotesCount[i];
        }
    }

    withdraw(amount: number): number[] {
        let ans = [0, 0, 0, 0, 0];
        for (let i = 4; i >= 0; i--) {
            ans[i] = Math.min(Math.floor(amount / this.d[i]), this.cnt[i]);
            amount -= ans[i] * this.d[i];
        }
        if (amount > 0) {
            return [-1];
        }
        for (let i = 0; i < ans.length; i++) {
            this.cnt[i] -= ans[i];
        }
        return ans;
    }
}

/**
 * Your ATM object will be instantiated and called as such:
 * var obj = new ATM()
 * obj.deposit(banknotesCount)
 * var param_2 = obj.withdraw(amount)
 */
