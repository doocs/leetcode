function minBitFlips(start, goal) {
    return (start ^ goal).toString(2).replace(/0/g, '').length;
}
