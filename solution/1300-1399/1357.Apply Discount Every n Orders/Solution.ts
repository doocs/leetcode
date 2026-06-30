class Cashier {
    i: number;
    n: number;
    discount: number;
    d: Map<number, number>;

    constructor(n: number, discount: number, products: number[], prices: number[]) {
        this.i = 0;
        this.n = n;
        this.discount = discount;
        this.d = new Map();
        for (let j = 0; j < products.length; j++) {
            this.d.set(products[j], prices[j]);
        }
    }

    getBill(product: number[], amount: number[]): number {
        this.i = (this.i + 1) % this.n;
        let x = 0;
        for (let j = 0; j < product.length; j++) {
            x += (this.d.get(product[j]) || 0) * amount[j];
        }
        if (this.i === 0) {
            return x - (this.discount * x) / 100;
        }
        return x;
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * var obj = new Cashier(n, discount, products, prices)
 * var param_1 = obj.getBill(product,amount)
 */
