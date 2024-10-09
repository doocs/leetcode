function convertDateToBinary(date: string): string {
    return date
        .split('-')
        .map(s => (+s).toString(2))
        .join('-');
}
