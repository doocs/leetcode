function complexNumberMultiply(num1: string, num2: string): string {
    let arr1 = num1.split('+'),
        arr2 = num2.split('+');
    let r1 = Number(arr1[0]),
        r2 = Number(arr2[0]);
    let v1 = Number(arr1[1].substring(0, arr1[1].length - 1)),
        v2 = Number(arr2[1].substring(0, arr2[1].length - 1));
    let ansR = r1 * r2 - v1 * v2;
    let ansV = r1 * v2 + r2 * v1;
    return `${ansR}+${ansV}i`;
}
