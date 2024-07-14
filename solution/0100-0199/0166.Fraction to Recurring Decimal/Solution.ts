function fractionToDecimal(numerator: number, denominator: number): string {
    if (numerator === 0) {
        return '0';
    }
    const sb: string[] = [];
    const neg: boolean = numerator > 0 !== denominator > 0;
    sb.push(neg ? '-' : '');
    let a: number = Math.abs(numerator),
        b: number = Math.abs(denominator);
    sb.push(Math.floor(a / b).toString());
    a %= b;
    if (a === 0) {
        return sb.join('');
    }
    sb.push('.');
    const d: Map<number, number> = new Map();
    while (a !== 0) {
        d.set(a, sb.length);
        a *= 10;
        sb.push(Math.floor(a / b).toString());
        a %= b;
        if (d.has(a)) {
            sb.splice(d.get(a)!, 0, '(');
            sb.push(')');
            break;
        }
    }
    return sb.join('');
}
