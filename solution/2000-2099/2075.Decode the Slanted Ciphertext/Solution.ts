function decodeCiphertext(encodedText: string, rows: number): string {
    const cols = Math.ceil(encodedText.length / rows);
    let ans = [];
    for (let k = 0; k <= cols; k++) {
        for (let i = 0, j = k; i < rows && j < cols; i++, j++) {
            ans.push(encodedText.charAt(i * cols + j));
        }
    }
    return ans.join('').trimEnd();
}
