function capitalizeTitle(title: string): string {
    const ans: string[] = [];
    for (const s of title.split(' ')) {
        if (s.length < 3) {
            ans.push(s.toLowerCase());
        } else {
            ans.push(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
        }
    }
    return ans.join(' ');
}
