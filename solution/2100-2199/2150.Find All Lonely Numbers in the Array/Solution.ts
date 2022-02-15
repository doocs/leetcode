function findLonely(nums: number[]): number[] {
    let hashMap: Map<number, number> = new Map();
    for (let num of nums) {
        hashMap.set(num, (hashMap.get(num) || 0) + 1);
    }
    let ans: Array<number> = [];
    for (let [num, count] of hashMap.entries()) {
        if (count == 1 && !hashMap.get(num - 1) && !hashMap.get(num + 1)) {
            ans.push(num);
        }
    }
    return ans;
}
