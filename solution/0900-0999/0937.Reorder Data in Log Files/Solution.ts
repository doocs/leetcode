function reorderLogFiles(logs: string[]): string[] {
    const isDigit = (c: string) => c >= '0' && c <= '9';
    return logs.sort((a, b) => {
        const end1 = a[a.length - 1];
        const end2 = b[b.length - 1];
        if (isDigit(end1) && isDigit(end2)) {
            return 0;
        }
        if (isDigit(end1)) {
            return 1;
        }
        if (isDigit(end2)) {
            return -1;
        }
        const content1 = a.split(' ').slice(1).join(' ');
        const content2 = b.split(' ').slice(1).join(' ');
        if (content1 === content2) {
            return a < b ? -1 : 1;
        }
        return content1 < content2 ? -1 : 1;
    });
}
