class StringIterator {
    private d: [string, number][] = [];
    private p: number = 0;

    constructor(compressedString: string) {
        const n = compressedString.length;
        let i = 0;
        while (i < n) {
            const c = compressedString[i];
            let x = 0;
            i++;
            while (i < n && !isNaN(Number(compressedString[i]))) {
                x = x * 10 + Number(compressedString[i]);
                i++;
            }
            this.d.push([c, x]);
        }
    }

    next(): string {
        if (!this.hasNext()) {
            return ' ';
        }
        const ans = this.d[this.p][0];
        this.d[this.p][1]--;
        if (this.d[this.p][1] === 0) {
            this.p++;
        }
        return ans;
    }

    hasNext(): boolean {
        return this.p < this.d.length && this.d[this.p][1] > 0;
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * var obj = new StringIterator(compressedString)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
