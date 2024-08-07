function numberToWords(num: number): string {
    if (num === 0) return 'Zero';

    // prettier-ignore
    const f = (x: number): string => {
    const dict1 = ['','One','Two','Three','Four','Five','Six','Seven','Eight','Nine','Ten','Eleven','Twelve','Thirteen','Fourteen','Fifteen','Sixteen','Seventeen','Eighteen','Nineteen',]
    const dict2 = ['','','Twenty','Thirty','Forty','Fifty','Sixty','Seventy','Eighty','Ninety',]
    let ans = ''

    if (x <= 19) ans = dict1[x] ?? ''
    else if (x < 100) ans = `${dict2[Math.floor(x / 10)]} ${f(x % 10)}`
    else if (x < 10 ** 3) ans = `${dict1[Math.floor(x / 100)]} Hundred ${f(x % 100)}`
    else if (x < 10 ** 6) ans = `${f(Math.floor(x / 10 ** 3))} Thousand ${f(x % 10 ** 3)}`
    else if (x < 10 ** 9) ans = `${f(Math.floor(x / 10 ** 6))} Million ${f(x % 10 ** 6)}`
    else ans = `${f(Math.floor(x / 10 ** 9))} Billion ${f(x % 10 ** 9)}`

    return ans.trim()
  }

    return f(num);
}
