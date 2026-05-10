function scoreValidator(events: string[]): number[] {
    let score = 0;
    let counter = 0;
    for (const event of events) {
        if (/^\d+$/.test(event)) {
            score += parseInt(event);
        } else if (event === 'W') {
            counter++;
            if (counter === 10) {
                break;
            }
        } else {
            score++;
        }
    }
    return [score, counter];
}
