class MyCalendarTwo {
    private tm: Record<number, number> = {};

    constructor() {}

    book(startTime: number, endTime: number): boolean {
        this.tm[startTime] = (this.tm[startTime] ?? 0) + 1;
        this.tm[endTime] = (this.tm[endTime] ?? 0) - 1;
        let s = 0;
        for (const v of Object.values(this.tm)) {
            s += v;
            if (s > 2) {
                if (--this.tm[startTime] === 0) {
                    delete this.tm[startTime];
                }
                if (++this.tm[endTime] === 0) {
                    delete this.tm[endTime];
                }
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
