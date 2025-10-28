/**
 * // Definition for an Interval.
 * class Interval {
 *    start: number;
 *    end: number;
 *    constructor(start: number, end: number) {
 *        this.start = start;
 *        this.end = end;
 *    }
 * }
 */
function employeeFreeTime(schedule: Interval[][]): Interval[] {
    const intervals: Interval[] = [];
    for (const e of schedule) {
        intervals.push(...e);
    }

    intervals.sort((a, b) => (a.start === b.start ? a.end - b.end : a.start - b.start));

    const merged: Interval[] = [intervals[0]];
    for (let i = 1; i < intervals.length; ++i) {
        const last = merged[merged.length - 1];
        const cur = intervals[i];
        if (last.end < cur.start) {
            merged.push(cur);
        } else {
            last.end = Math.max(last.end, cur.end);
        }
    }

    const ans: Interval[] = [];
    for (let i = 1; i < merged.length; ++i) {
        const a = merged[i - 1];
        const b = merged[i];
        ans.push(new Interval(a.end, b.start));
    }

    return ans;
}
