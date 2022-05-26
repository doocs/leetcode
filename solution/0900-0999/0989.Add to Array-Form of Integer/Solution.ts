function addToArrayForm(num: number[], k: number): number[] {
    let arr2 = [...String(k)].map(Number);
    let ans = [];
    let sum = 0;
    while (num.length || arr2.length || sum) {
        let a = num.pop() || 0,
            b = arr2.pop() || 0;
        sum += a + b;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans;
}
