function haveConflict(event1: string[], event2: string[]): boolean {
    return event1[0] <= event2[1] && event1[1] >= event2[0];
}
