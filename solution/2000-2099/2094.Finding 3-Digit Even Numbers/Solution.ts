function findEvenNumbers(digits: number[]): number[] {
    let record = new Array(10).fill(0);
    for (let digit of digits) {
        record[digit]++;
    }
    let ans = [];
    for (let i = 100; i < 1000; i += 2) {
        if (check(record, String(i))) {
            ans.push(i);
        }
    }
    return ans;
}

function check(target: Array<number>, digits: string): boolean {
    let record = new Array(10).fill(0);
    for (let digit of digits) {
        record[digit]++;
    }

    for (let i = 0; i < 10; i++) {
        if (record[i] > target[i]) return false;
    }
    return true;
}
