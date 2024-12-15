function continuousSubarrays(nums: number[]): number {
    const [minQ, maxQ]: [number[], number[]] = [[], []];
    const n = nums.length;
    let res = 0;

    for (let r = 0, l = 0; r < n; r++) {
        const x = nums[r];
        while (minQ.length && nums[minQ.at(-1)!] > x) minQ.pop();
        while (maxQ.length && nums[maxQ.at(-1)!] < x) maxQ.pop();
        minQ.push(r);
        maxQ.push(r);

        while (minQ.length && maxQ.length && nums[maxQ[0]] - nums[minQ[0]] > 2) {
            if (maxQ[0] < minQ[0]) {
                l = maxQ[0] + 1;
                maxQ.shift();
            } else {
                l = minQ[0] + 1;
                minQ.shift();
            }
        }

        res += r - l + 1;
    }

    return res;
}
