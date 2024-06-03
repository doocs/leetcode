function validUtf8(data: number[]): boolean {
    let cnt = 0;
    for (const v of data) {
        if (cnt > 0) {
            if (v >> 6 !== 0b10) {
                return false;
            }
            --cnt;
        } else if (v >> 7 === 0) {
            cnt = 0;
        } else if (v >> 5 === 0b110) {
            cnt = 1;
        } else if (v >> 4 === 0b1110) {
            cnt = 2;
        } else if (v >> 3 === 0b11110) {
            cnt = 3;
        } else {
            return false;
        }
    }
    return cnt === 0;
}
