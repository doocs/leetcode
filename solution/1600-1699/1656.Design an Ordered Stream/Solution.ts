class OrderedStream {
    private ptr: number;
    private data: string[];

    constructor(n: number) {
        this.ptr = 1;
        this.data = Array(n + 1);
    }

    insert(idKey: number, value: string): string[] {
        this.data[idKey] = value;
        const ans: string[] = [];
        while (this.data[this.ptr]) {
            ans.push(this.data[this.ptr++]);
        }
        return ans;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * var obj = new OrderedStream(n)
 * var param_1 = obj.insert(idKey,value)
 */
