function compressedString(word) {
    const regex = /(.)\1{0,8}/g;
    let m = null;
    let res = '';

    while ((m = regex.exec(word))) {
        res += m[0].length + m[1];
    }

    return res;
}
