function orchestraLayout(num: number, xPos: number, yPos: number): number {
    const layer = Math.min(xPos, yPos, num - 1 - xPos, num - 1 - yPos);
    const side = num - 2 * layer;
    const start = ((((4 * (layer % 9)) % 9) * ((num - layer) % 9)) % 9) + 1;

    let offset: number;
    if (xPos === layer) {
        offset = yPos - layer;
    } else if (yPos === num - layer - 1) {
        offset = side - 1 + xPos - layer;
    } else if (xPos === num - layer - 1) {
        offset = 2 * side - 2 + num - layer - 1 - yPos;
    } else {
        offset = 3 * side - 3 + num - layer - 1 - xPos;
    }

    return ((start - 1 + offset) % 9) + 1;
}
