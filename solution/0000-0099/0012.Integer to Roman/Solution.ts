function intToRoman(num: number): string {
    const nums: number[] = [
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1,
    ];
    const romans: string[] = [
        'M',
        'CM',
        'D',
        'CD',
        'C',
        'XC',
        'L',
        'XL',
        'X',
        'IX',
        'V',
        'IV',
        'I',
    ];
    let ans: string = '';
    for (let i = 0; i < nums.length; ++i) {
        while (num >= nums[i]) {
            num -= nums[i];
            ans += romans[i];
        }
    }
    return ans;
}
