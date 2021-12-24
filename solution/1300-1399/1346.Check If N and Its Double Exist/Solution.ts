function checkIfExist(arr: number[]): boolean {
    for (let i = arr.length - 1; i >= 0; --i) {
        let cur = arr[i];
        let t1 = 2 * cur;
        if (arr.includes(t1) && arr.indexOf(t1) != i) {
            return true;
        }
        let t2 = cur >> 1;
        if (cur % 2 == 0 && arr.includes(t2) && arr.indexOf(t2) != i) {
            return true;
        }
    }
    return false;
}
