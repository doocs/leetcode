function numberOfChild(n: number, k: number): number {
    const mod = k % (n - 1);
    k = (k / (n - 1)) | 0;
    return k % 2 ? n - mod - 1 : mod;
}
