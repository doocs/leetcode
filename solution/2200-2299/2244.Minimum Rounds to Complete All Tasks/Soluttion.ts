function minimumRounds(tasks: number[]): number {
    let hashMap = new Map();
    for (let key of tasks) {
        hashMap.set(key, (hashMap.get(key) || 0) + 1);
    }
    let ans = 0;
    for (let key of hashMap.keys()) {
        let val = hashMap.get(key);
        if (val < 2) return -1;
        const ctn = Math.floor(val / 3) + (val % 3 == 0 ? 0 : 1);
        ans += ctn;
    }
    return ans;
}
