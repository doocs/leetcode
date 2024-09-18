function largestNumber(nums) {
    nums.sort((a, b) => {
        const [ab, ba] = [String(a) + String(b), String(b) + String(a)];
        return +ba - +ab;
    });

    return nums[0] ? nums.join('') : '0';
}
