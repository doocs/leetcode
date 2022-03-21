function addStrings(num1: string, num2: string): string {
    const res = [];
    let i = num1.length - 1;
    let j = num2.length - 1;
    let isOver = false;
    while (i >= 0 || j >= 0 || isOver) {
        const x = Number(num1[i--]) || 0;
        const y = Number(num2[j--]) || 0;
        const sum = x + y + (isOver ? 1 : 0);
        isOver = sum >= 10;
        res.push(sum % 10);
    }
    return res.reverse().join('');
}
