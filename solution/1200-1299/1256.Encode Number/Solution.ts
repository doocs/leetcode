function encode(num: number): string {
    ++num;
    let s = num.toString(2);
    return s.slice(1);
}
