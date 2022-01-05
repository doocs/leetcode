function modifyString(s: string): string {
    const strArr = ["a", "b", "c"];
    const n = s.length;

    const result = [];
    for (let i = 0; i < n; i++) {
        let str = s[i];
        if (str === "?") {
            const before = result[i - 1];
            const after = s[i + 1];

            if (after !== "a" && before !== "a") {
                str = strArr[0];
            } else if (after !== "b" && before !== "b") {
                str = strArr[1];
            } else {
                str = strArr[2];
            }
        }

        result.push(str);
    }
    return result.join("");
}
