function kthGrammar(n: number, k: number): number {
    if (n == 1) {
        return 0;
    }
    if (k <= 1 << (n - 2)) {
        return kthGrammar(n - 1, k);
    }
    return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
}
