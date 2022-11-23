function findTheDifference(s: string, t: string): string {
    return String.fromCharCode(
        [...t].reduce((r, v) => r + v.charCodeAt(0), 0) -
            [...s].reduce((r, v) => r + v.charCodeAt(0), 0),
    );
}
