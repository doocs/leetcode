function exclusiveTime(n: number, logs: string[]): number[] {
    const res = new Array(n).fill(0);
    const stack: [number, number][] = [];

    for (const log of logs) {
        const t = log.split(':');
        const [id, state, time] = [Number(t[0]), t[1], Number(t[2])];

        if (state === 'start') {
            if (stack.length !== 0) {
                const pre = stack[stack.length - 1];
                res[pre[0]] += time - pre[1];
            }
            stack.push([id, time]);
        } else {
            const pre = stack.pop();
            res[pre[0]] += time - pre[1] + 1;
            if (stack.length !== 0) {
                stack[stack.length - 1][1] = time + 1;
            }
        }
    }

    return res;
}
