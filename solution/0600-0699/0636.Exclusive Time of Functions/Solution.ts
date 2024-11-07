function exclusiveTime(n: number, logs: string[]): number[] {
    const ans: number[] = Array(n).fill(0);
    let pre = 0;
    const stk: number[] = [];
    for (const log of logs) {
        const [i, op, cur] = log.split(':');
        if (op[0] === 's') {
            if (stk.length) {
                ans[stk.at(-1)!] += +cur - pre;
            }
            stk.push(+i);
            pre = +cur;
        } else {
            ans[stk.pop()!] += +cur - pre + 1;
            pre = +cur + 1;
        }
    }
    return ans;
}
