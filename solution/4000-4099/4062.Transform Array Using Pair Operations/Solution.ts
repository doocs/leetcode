function canTransform(source: number[], target: number[]): boolean {
    return (
        source.reduce((s, x) => s + BigInt(x), 0n) === target.reduce((s, x) => s + BigInt(x), 0n)
    );
}
