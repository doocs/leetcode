function replaceSpaces(S: string, length: number): string {
    return S.slice(0, length).replace(/\s/g, '%20');
}
