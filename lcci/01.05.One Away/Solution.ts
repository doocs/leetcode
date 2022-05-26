function oneEditAway(first: string, second: string): boolean {
    const n = first.length;
    const m = second.length;

    let count = 0;
    let i = 0;
    let j = 0;
    while (i < n || j < m) {
        if (first[i] !== second[j]) {
            count++;

            if (i < n && first[i + 1] === second[j]) {
                i++;
            } else if (j < m && first[i] === second[j + 1]) {
                j++;
            }
        }
        i++;
        j++;
    }
    return count <= 1;
}
