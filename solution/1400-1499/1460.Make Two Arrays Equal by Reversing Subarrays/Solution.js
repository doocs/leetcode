function canBeEqual(target, arr) {
    target.sort();
    arr.sort();
    return target.every((x, i) => x === arr[i]);
}
