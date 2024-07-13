function discountPrices(sentence: string, discount: number): string {
    const sell = (100 - discount) / 100;
    const reg = new RegExp(/^(\$)(([1-9]\d*\.?\d*)|(0\.\d*))$/g);
    const words = sentence.split(' ').map(d => {
        if (!reg.test(d)) return d;
        return d.replace(reg, (s, $1, $2) => {
            return `$${(sell * $2).toFixed(2)}`;
        });
    });
    return words.join(' ');
}
