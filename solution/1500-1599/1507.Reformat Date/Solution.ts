function reformatDate(date: string): string {
    const s = date.split(' ');
    const months = ' JanFebMarAprMayJunJulAugSepOctNovDec';
    const day = parseInt(s[0].substring(0, s[0].length - 2));
    const month = Math.floor(months.indexOf(s[1]) / 3) + 1;
    return `${s[2]}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
}
