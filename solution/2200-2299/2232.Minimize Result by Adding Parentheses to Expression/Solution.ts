function minimizeResult(expression: string): string {
    const [n1, n2] = expression.split('+');
    let minSum = Number.MAX_SAFE_INTEGER;
    let ans = '';
    let arr1 = [],
        arr2 = n1.split(''),
        arr3 = n2.split(''),
        arr4 = [];
    while (arr2.length) {
        (arr3 = n2.split('')), (arr4 = []);
        while (arr3.length) {
            let cur =
                (getNum(arr2) + getNum(arr3)) * getNum(arr1) * getNum(arr4);
            if (cur < minSum) {
                minSum = cur;
                ans = `${arr1.join('')}(${arr2.join('')}+${arr3.join(
                    '',
                )})${arr4.join('')}`;
            }
            arr4.unshift(arr3.pop());
        }
        arr1.push(arr2.shift());
    }
    return ans;
}

function getNum(arr: Array<string>): number {
    return arr.length ? Number(arr.join('')) : 1;
}
