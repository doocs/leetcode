function verifyPostorder(postorder: number[]): boolean {
    let mx = 1 << 30;
    const stk: number[] = [];
    for (let i = postorder.length - 1; i >= 0; --i) {
        const x = postorder[i];
        if (x > mx) {
            return false;
        }
        while (stk.length && stk[stk.length - 1] > x) {
            mx = stk.pop();
        }
        stk.push(x);
    }
    return true;
}
