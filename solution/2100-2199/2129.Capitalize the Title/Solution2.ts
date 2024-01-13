function capitalizeTitle(title: string): string {
    return title
        .split(' ')
        .map(s =>
            s.length < 3
                ? s.toLowerCase()
                : s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase(),
        )
        .join(' ');
}
