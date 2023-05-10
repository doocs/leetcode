function checkRecord(s: string): boolean {
    return s.indexOf('A') === s.lastIndexOf('A') && s.indexOf('LLL') === -1;
}
