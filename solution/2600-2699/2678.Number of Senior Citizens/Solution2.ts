function countSeniors(details: string[]): number {
    return details.filter(v => parseInt(v.slice(11, 13)) > 60).length;
}
