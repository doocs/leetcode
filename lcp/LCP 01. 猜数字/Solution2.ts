function game(guess: number[], answer: number[]): number {
    return guess.reduce((acc, cur, index) => (cur === answer[index] ? acc + 1 : acc), 0);
}
