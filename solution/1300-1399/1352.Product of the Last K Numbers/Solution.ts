class ProductOfNumbers {
    s = [1];

    add(num: number): void {
        if (num === 0) {
            this.s = [1];
        } else {
            const i = this.s.length;
            this.s[i] = this.s[i - 1] * num;
        }
    }

    getProduct(k: number): number {
        const i = this.s.length;
        if (k > i - 1) return 0;
        return this.s[i - 1] / this.s[i - k - 1];
    }
}
