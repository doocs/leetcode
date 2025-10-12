class ExamTracker {
    private times: number[] = [0];
    private pre: number[] = [0];
    constructor() {}

    record(time: number, score: number): void {
        this.times.push(time);
        this.pre.push(this.pre.at(-1)! + score);
    }

    totalScore(startTime: number, endTime: number): number {
        const l = _.sortedIndex(this.times, startTime) - 1;
        const r = _.sortedIndex(this.times, endTime + 1) - 1;
        return this.pre[r] - this.pre[l];
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * var obj = new ExamTracker()
 * obj.record(time,score)
 * var param_2 = obj.totalScore(startTime,endTime)
 */
