function minimumDeletions(s: string): number {
    const stk: string[] = [];
    let res = 0;

    for (const ch of s) {
        if (stk.at(-1) === 'b' && ch === 'a') {
            stk.pop();
            res++;
        } else stk.push(ch);
    }

    return res;
}
