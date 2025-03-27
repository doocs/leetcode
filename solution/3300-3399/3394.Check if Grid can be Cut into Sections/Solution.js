function checkValidCuts(n, rectangles) {
    const check = (arr, getVals) => {
        let [c, longest] = [3, 0];

        for (const x of arr) {
            const [start, end] = getVals(x);

            if (start < longest) {
                longest = Math.max(longest, end);
            } else {
                longest = end;
                if (--c === 0) return true;
            }
        }

        return false;
    };

    const sortByX = ([a], [b]) => a - b;
    const sortByY = ([, a], [, b]) => a - b;
    const getX = ([x1, , x2]) => [x1, x2];
    const getY = ([, y1, , y2]) => [y1, y2];

    return check(rectangles.toSorted(sortByX), getX) || check(rectangles.toSorted(sortByY), getY);
}
