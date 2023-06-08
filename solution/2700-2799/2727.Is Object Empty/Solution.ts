function isEmpty(obj: Record<string, any> | any[]): boolean {
    for (const x in obj) {
        return false;
    }
    return true;
}
