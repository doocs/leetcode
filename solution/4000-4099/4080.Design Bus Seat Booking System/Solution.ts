class BusBooking {
    private tot: number;
    private minTot: number;
    private costData: number[][];

    constructor(n: number) {
        this.tot = 0;
        this.minTot = 0;
        this.costData = Array.from({ length: n }, () => new Array<number>(4).fill(0));
    }

    toggle(seat: string): void {
        let i = 0;
        for (let k = 1; k < seat.length; k++) {
            i = i * 10 + (seat.charCodeAt(k) - '0'.charCodeAt(0));
        }
        i -= 1;
        const j = seat.charCodeAt(0) - 'A'.charCodeAt(0);

        if (this.costData[i][j] > 0) {
            this.minTot -= 1;
            this.tot -= this.costData[i][j];
            this.costData[i][j] = 0;
            return;
        }

        let cost = 1;
        if ((j === 0 && this.costData[i][1] > 0) || (j === 3 && this.costData[i][2] > 0)) {
            cost = 3;
        }
        this.minTot += 1;
        this.tot += cost;
        this.costData[i][j] = cost;
    }

    getTotalTime(): number {
        return this.tot;
    }

    getMinTime(): number {
        return this.minTot;
    }
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * var obj = new BusBooking(n)
 * obj.toggle(seat)
 * var param_2 = obj.getTotalTime()
 * var param_3 = obj.getMinTime()
 */
