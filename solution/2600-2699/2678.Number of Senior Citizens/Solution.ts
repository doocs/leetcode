function countSeniors(details: string[]): number {
    return details.filter(x => +x.slice(11, 13) > 60).length;
}
