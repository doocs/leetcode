function subarraySum(nums: number[], k: number): number {
    let ans = 0,
        pre = 0;
    let hashTable = new Map();
    hashTable.set(0, 1);
    for (let num of nums) {
        pre += num;
        ans += hashTable.get(pre - k) || 0;
        hashTable.set(pre, (hashTable.get(pre) || 0) + 1);
    }
    return ans;
}
