function reformatNumber(number: string): string {
    const cs = [...number].filter(c => c !== ' ' && c !== '-');
    const n = cs.length;
    return cs
        .map((v, i) => {
            if (
                ((i + 1) % 3 === 0 && i < n - 2) ||
                (n % 3 === 1 && n - 3 === i)
            ) {
                return v + '-';
            }
            return v;
        })
        .join('');
}
