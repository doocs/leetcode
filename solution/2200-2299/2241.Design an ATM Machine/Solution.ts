const d: number[] = [20, 50, 100, 200, 500];
const m = d.length;

class ATM {
    private cnt: number[];

    constructor() {
        this.cnt = Array(m).fill(0);
    }

    deposit(banknotesCount: number[]): void {
        for (let i = 0; i < banknotesCount.length; ++i) {
            this.cnt[i] += banknotesCount[i];
        }
    }

    withdraw(amount: number): number[] {
        const ans: number[] = Array(m).fill(0);
        for (let i = m - 1; i >= 0; --i) {
            ans[i] = Math.min(Math.floor(amount / d[i]), this.cnt[i]);
            amount -= ans[i] * d[i];
        }
        if (amount > 0) {
            return [-1];
        }
        for (let i = 0; i < m; ++i) {
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
