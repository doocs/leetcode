function generateKey(num1: number, num2: number, num3: number): number {
    let [ans, k] = [0, 1];
    for (let i = 0; i < 4; ++i) {
        const x = Math.min(((num1 / k) | 0) % 10, ((num2 / k) | 0) % 10, ((num3 / k) | 0) % 10);
        ans += x * k;
        k *= 10;
    }
    return ans;
}
