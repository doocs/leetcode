function numUniqueEmails(emails: string[]): number {
    return new Set(
        emails
            .map(email => email.split('@'))
            .map(([start, end]) => start.replace(/\+.*|\./g, '') + '@' + end),
    ).size;
}
