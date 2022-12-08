function squareIsWhite(coordinates: string): boolean {
    return ((coordinates.charCodeAt(0) + coordinates.charCodeAt(1)) & 1) === 1;
}
