function numberToWords(num: number): string {
    if (num === 0) {
        return 'Zero';
    }

    const lt20 = [
        '',
        'One',
        'Two',
        'Three',
        'Four',
        'Five',
        'Six',
        'Seven',
        'Eight',
        'Nine',
        'Ten',
        'Eleven',
        'Twelve',
        'Thirteen',
        'Fourteen',
        'Fifteen',
        'Sixteen',
        'Seventeen',
        'Eighteen',
        'Nineteen',
    ];

    const tens = [
        '',
        'Ten',
        'Twenty',
        'Thirty',
        'Forty',
        'Fifty',
        'Sixty',
        'Seventy',
        'Eighty',
        'Ninety',
    ];

    const thousands = ['Billion', 'Million', 'Thousand', ''];

    const transfer = (n: number): string => {
        if (n === 0) {
            return '';
        }
        if (n < 20) {
            return lt20[n];
        }
        if (n < 100) {
            if (n % 10 === 0) {
                return tens[Math.floor(n / 10)];
            }
            return tens[Math.floor(n / 10)] + ' ' + transfer(n % 10);
        }
        if (n % 100 === 0) {
            return lt20[Math.floor(n / 100)] + ' Hundred';
        }
        return lt20[Math.floor(n / 100)] + ' Hundred ' + transfer(n % 100);
    };

    let res = '';
    for (let i = 1_000_000_000, j = 0; i > 0; i = Math.floor(i / 1000), ++j) {
        const cur = Math.floor(num / i);
        if (cur === 0) {
            continue;
        }
        if (res !== '') {
            res += ' ';
        }
        res += transfer(cur);
        if (thousands[j] !== '') {
            res += ' ' + thousands[j];
        }
        num %= i;
    }
    return res;
}
