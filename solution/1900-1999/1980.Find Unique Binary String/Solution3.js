function findDifferentBinaryString(nums) {
    const res = [];

    for (let i = 0; i < nums.length; i++) {
        const x = nums[i][i];
        res.push(x === '0' ? '1' : '0');
    }

    return res.join('');
}
