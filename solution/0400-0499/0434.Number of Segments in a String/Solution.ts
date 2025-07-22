function countSegments(s: string): number {
    return s.split(/\s+/).filter(Boolean).length;
}
