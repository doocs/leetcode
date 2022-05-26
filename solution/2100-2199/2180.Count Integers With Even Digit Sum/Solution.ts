function countEven(num: number): number {
    let ans = 0;
    for (let i = 2; i <= num; i++) {
        if ([...String(i)].reduce((a, c) => a + Number(c), 0) % 2 == 0) {
            ans++;
        }
    }
    return ans;
}
