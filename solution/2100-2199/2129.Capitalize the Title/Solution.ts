function capitalizeTitle(title: string): string {
    return title
        .split(' ')
        .map(s =>
            s.length < 3 ? s.toLowerCase() : s.slice(0, 1).toUpperCase() + s.slice(1).toLowerCase(),
        )
        .join(' ');
}
