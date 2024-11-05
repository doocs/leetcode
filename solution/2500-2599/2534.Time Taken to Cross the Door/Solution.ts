function timeTaken(arrival: number[], state: number[]): number[] {
    const n = arrival.length;
    const q: number[][] = [[], []];
    let [t, i, st] = [0, 0, 1];
    const ans: number[] = Array(n).fill(0);

    while (i < n || q[0].length || q[1].length) {
        while (i < n && arrival[i] <= t) {
            q[state[i]].push(i++);
        }

        if (q[0].length && q[1].length) {
            ans[q[st][0]] = t;
            q[st].shift();
        } else if (q[0].length || q[1].length) {
            st = q[0].length ? 0 : 1;
            ans[q[st][0]] = t;
            q[st].shift();
        } else {
            st = 1;
        }

        t++;
    }

    return ans;
}
