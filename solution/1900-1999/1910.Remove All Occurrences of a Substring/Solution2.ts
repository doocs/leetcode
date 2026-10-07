function removeOccurrences(s: string, part: string): string {
    const m = part.length;
    const st: string[] = [];
    for (const c of s) {
        st.push(c);
        if (st.length >= m && st.slice(-m).join('') === part) {
            st.length -= m;
        }
    }
    return st.join('');
}
