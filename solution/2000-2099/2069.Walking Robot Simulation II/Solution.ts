class Robot {
    private mx: number;
    private my: number;
    private p: number;
    private cur: number;
    private moved: boolean;

    constructor(width: number, height: number) {
        this.mx = width - 1;
        this.my = height - 1;
        this.p = 2 * this.mx + 2 * this.my;
        this.cur = 0;
        this.moved = false;
    }

    step(num: number): void {
        this.moved = true;
        this.cur = (this.cur + num) % this.p;
    }

    getPos(): number[] {
        const d = this.cur;
        const mx = this.mx,
            my = this.my;

        if (0 <= d && d <= mx) {
            return [d, 0];
        }
        if (mx < d && d <= mx + my) {
            return [mx, d - mx];
        }
        if (mx + my < d && d <= 2 * mx + my) {
            return [mx - (d - (mx + my)), my];
        }
        return [0, my - (d - (2 * mx + my))];
    }

    getDir(): string {
        const d = this.cur;
        const mx = this.mx,
            my = this.my;

        if (!this.moved) {
            return 'East';
        }
        if (1 <= d && d <= mx) {
            return 'East';
        } else if (mx < d && d <= mx + my) {
            return 'North';
        } else if (mx + my < d && d <= 2 * mx + my) {
            return 'West';
        }
        return 'South';
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * var obj = new Robot(width, height)
 * obj.step(num)
 * var param_2 = obj.getPos()
 * var param_3 = obj.getDir()
 */
