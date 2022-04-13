function largestInteger(num: number): number {
    let arrs = String(num).split('').map(Number);
    let odds = []; // 奇数
    let evens = [];
    for (let i of arrs) {
        if ((i & 1) == 1) {
            odds.push(i);
        } else {
            evens.push(i);
        }
    }
    odds.sort((a, b) => a - b);
    evens.sort((a, b) => a - b);
    let ans = [];
    for (let i of arrs) {
        ans.push((i & 1) == 1 ? odds.pop() : evens.pop());
    }
    return Number(ans.join(''));
}
