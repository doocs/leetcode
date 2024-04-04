class TripleInOne {
    private cap: number;
    private stk: number[];

    constructor(stackSize: number) {
        this.cap = stackSize;
        this.stk = Array<number>(stackSize * 3 + 3).fill(0);
    }

    push(stackNum: number, value: number): void {
        if (this.stk[this.cap * 3 + stackNum] < this.cap) {
            this.stk[this.cap * stackNum + this.stk[this.cap * 3 + stackNum]] = value;
            this.stk[this.cap * 3 + stackNum]++;
        }
    }

    pop(stackNum: number): number {
        if (this.isEmpty(stackNum)) {
            return -1;
        }
        this.stk[this.cap * 3 + stackNum]--;
        return this.stk[this.cap * stackNum + this.stk[this.cap * 3 + stackNum]];
    }

    peek(stackNum: number): number {
        if (this.isEmpty(stackNum)) {
            return -1;
        }
        return this.stk[this.cap * stackNum + this.stk[this.cap * 3 + stackNum] - 1];
    }

    isEmpty(stackNum: number): boolean {
        return this.stk[this.cap * 3 + stackNum] === 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * var obj = new TripleInOne(stackSize)
 * obj.push(stackNum,value)
 * var param_2 = obj.pop(stackNum)
 * var param_3 = obj.peek(stackNum)
 * var param_4 = obj.isEmpty(stackNum)
 */
