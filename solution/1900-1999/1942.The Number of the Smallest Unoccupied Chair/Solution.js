/**
 * @param {number[][]} times
 * @param {number} targetFriend
 * @return {number}
 */
var smallestChair = function (times, targetFriend) {
    const n = times.length;
    const idle = new MinPriorityQueue();
    const busy = new MinPriorityQueue({ priority: v => v[0] });
    for (let i = 0; i < n; ++i) {
        times[i].push(i);
        idle.enqueue(i);
    }
    times.sort((a, b) => a[0] - b[0]);
    for (const [arrival, leaving, i] of times) {
        while (busy.size() > 0 && busy.front().element[0] <= arrival) {
            idle.enqueue(busy.dequeue().element[1]);
        }
        const j = idle.dequeue().element;
        if (i === targetFriend) {
            return j;
        }
        busy.enqueue([leaving, j]);
    }
};
