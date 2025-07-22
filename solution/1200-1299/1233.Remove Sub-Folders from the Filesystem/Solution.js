function removeSubfolders(folder) {
    let s = folder[1];
    return folder.sort().filter(x => !x.startsWith(s + '/') && (s = x));
}
