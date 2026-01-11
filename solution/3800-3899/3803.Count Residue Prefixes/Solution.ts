function residuePrefixes(s: string): number {
    const st = new Set<string>();
    let ans = 0;
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        st.add(c);
        if (st.size === (i + 1) % 3) {
            ans++;
        }
    }
    return ans;
}
