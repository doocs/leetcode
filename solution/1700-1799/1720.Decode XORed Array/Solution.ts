function decode(encoded: number[], first: number): number[] {
    const ans: number[] = [first];
    for (const x of encoded) {
        ans.push(ans.at(-1)! ^ x);
    }
    return ans;
}
