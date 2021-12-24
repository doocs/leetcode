function topKFrequent(nums: number[], k: number): number[] {
    let hashMap = new Map();
    for (let num of nums) {
        hashMap.set(num, (hashMap.get(num) || 0) + 1);
    }
    let list = [...hashMap];
    list.sort((a, b) => b[1] - a[1]);
    let ans = [];
    for (let i = 0; i < k; i++) {
        ans.push(list[i][0]);
    }
    return ans;
}
