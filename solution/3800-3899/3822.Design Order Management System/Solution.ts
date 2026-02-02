class OrderManagementSystem {
    private orderTypeMap: Map<number, string>;
    private priceMap: Map<number, number>;
    private t: Map<string, number[]>;

    constructor() {
        this.orderTypeMap = new Map();
        this.priceMap = new Map();
        this.t = new Map();
    }

    private key(orderType: string, price: number): string {
        return `${orderType}#${price}`;
    }

    addOrder(orderId: number, orderType: string, price: number): void {
        this.orderTypeMap.set(orderId, orderType);
        this.priceMap.set(orderId, price);

        const k = this.key(orderType, price);
        if (!this.t.has(k)) {
            this.t.set(k, []);
        }
        this.t.get(k)!.push(orderId);
    }

    modifyOrder(orderId: number, newPrice: number): void {
        const orderType = this.orderTypeMap.get(orderId)!;
        const oldPrice = this.priceMap.get(orderId)!;

        this.priceMap.set(orderId, newPrice);

        const oldKey = this.key(orderType, oldPrice);
        const oldList = this.t.get(oldKey)!;
        const idx = oldList.indexOf(orderId);
        if (idx !== -1) {
            oldList.splice(idx, 1);
        }

        const newKey = this.key(orderType, newPrice);
        if (!this.t.has(newKey)) {
            this.t.set(newKey, []);
        }
        this.t.get(newKey)!.push(orderId);
    }

    cancelOrder(orderId: number): void {
        const orderType = this.orderTypeMap.get(orderId)!;
        const price = this.priceMap.get(orderId)!;

        this.orderTypeMap.delete(orderId);
        this.priceMap.delete(orderId);

        const k = this.key(orderType, price);
        const list = this.t.get(k)!;
        const idx = list.indexOf(orderId);
        if (idx !== -1) {
            list.splice(idx, 1);
        }
    }

    getOrdersAtPrice(orderType: string, price: number): number[] {
        return this.t.get(this.key(orderType, price)) ?? [];
    }
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * var obj = new OrderManagementSystem()
 * obj.addOrder(orderId,orderType,price)
 * obj.modifyOrder(orderId,newPrice)
 * obj.cancelOrder(orderId)
 * var param_4 = obj.getOrdersAtPrice(orderType,price)
 */
