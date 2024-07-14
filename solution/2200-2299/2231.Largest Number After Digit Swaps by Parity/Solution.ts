function largestInteger(num: number): number {
    const arrs: number[] = String(num).split('').map(Number);
    const odds: number[] = []; // 奇数
    const evens: number[] = [];
    for (const i of arrs) {
        if ((i & 1) == 1) {
            odds.push(i);
        } else {
            evens.push(i);
        }
    }
    odds.sort((a, b) => a - b);
    evens.sort((a, b) => a - b);
    const ans: number[] = [];
    for (const i of arrs) {
        ans.push((i & 1) === 1 ? odds.pop() : evens.pop());
    }
    return Number(ans.join(''));
}
