function countTasks(tasks: number[], shifts: number[]): number[] {
    const m = tasks.length;
    const n = shifts.length;

    const s = new Array<number>(m + 1).fill(0);
    for (let i = 0; i < m; i++) {
        s[i + 1] = s[i] + tasks[i];
    }

    const ans = new Array<number>(n).fill(0);

    let i = 0;
    let cur = 0;

    for (let j = 0; j < n; j++) {
        if (shifts[j] < tasks[i] - cur) {
            cur += shifts[j];
            ans[j] = m - i;
        } else {
            const t = shifts[j] - (tasks[i] - cur);

            if (t >= s[m] - s[i + 1]) {
                i = 0;
                cur = 0;
            } else {
                let l = i + 1;
                let r = m;

                while (l < r) {
                    const mid = (l + r) >> 1;
                    if (t < s[mid + 1] - s[i + 1]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }

                cur = t - (s[l] - s[i + 1]);
                i = l;
                ans[j] = m - i;
            }
        }
    }

    return ans;
}
