function removeSubfolders(folder: string[]): string[] {
    let s = folder[1];
    return folder.sort().filter(x => !x.startsWith(s + '/') && (s = x));
}
