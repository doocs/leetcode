function readBinaryWatch(turnedOn: number): string[] {
    if (turnedOn === 0) {
        return ['0:00'];
    }
    const n = 10;
    const res = [];
    const bitArr = new Array(10).fill(false);
    const createTime = () => {
        return [
            bitArr.slice(0, 4).reduce((p, v) => (p << 1) | Number(v), 0),
            bitArr.slice(4).reduce((p, v) => (p << 1) | Number(v), 0),
        ];
    };
    const helper = (i: number, count: number) => {
        if (i + count > n || count === 0) {
            return;
        }
        bitArr[i] = true;
        if (count === 1) {
            const [h, m] = createTime();
            if (h < 12 && m < 60) {
                res.push(`${h}:${m < 10 ? '0' + m : m}`);
            }
        }
        helper(i + 1, count - 1);
        bitArr[i] = false;
        helper(i + 1, count);
    };
    helper(0, turnedOn);
    return res;
}
