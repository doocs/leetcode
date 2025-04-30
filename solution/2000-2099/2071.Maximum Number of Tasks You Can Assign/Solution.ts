function maxTaskAssign(
    tasks: number[],
    workers: number[],
    pills: number,
    strength: number,
): number {
    tasks.sort((a, b) => a - b);
    workers.sort((a, b) => a - b);

    const n = tasks.length;
    const m = workers.length;

    const check = (x: number): boolean => {
        const dq = new Array<number>(x);
        let head = 0;
        let tail = 0;
        const empty = () => head === tail;
        const pushBack = (val: number) => {
            dq[tail++] = val;
        };
        const popFront = () => {
            head++;
        };
        const popBack = () => {
            tail--;
        };
        const front = () => dq[head];

        let i = 0;
        let p = pills;

        for (let j = m - x; j < m; j++) {
            while (i < x && tasks[i] <= workers[j] + strength) {
                pushBack(tasks[i]);
                i++;
            }

            if (empty()) return false;

            if (front() <= workers[j]) {
                popFront();
            } else {
                if (p === 0) return false;
                p--;
                popBack();
            }
        }
        return true;
    };

    let [left, right] = [0, Math.min(n, m)];
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) left = mid;
        else right = mid - 1;
    }
    return left;
}
