function twoOutOfThree(nums1: number[], nums2: number[], nums3: number[]): number[] {
    const get = (nums: number[]): number[] => {
        const s = new Array(101).fill(0);
        for (const v of nums) {
            s[v] = 1;
        }
        return s;
    };

    const s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
    const ans: number[] = [];

    for (let i = 1; i <= 100; i++) {
        if (s1[i] + s2[i] + s3[i] > 1) {
            ans.push(i);
        }
    }
    return ans;
};
