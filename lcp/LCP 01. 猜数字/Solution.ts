function game(guess: number[], answer: number[]): number {
    let ans = 0;
    for (let i = 0; i < 3; ++i) {
        if (guess[i] === answer[i]) {
            ++ans;
        }
    }
    return ans;
}
