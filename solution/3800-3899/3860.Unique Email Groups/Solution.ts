function uniqueEmailGroups(emails: string[]): number {
    const st = new Set<string>();

    for (const email of emails) {
        let [local, domain] = email.split('@');
        local = local.split('+')[0].replace(/\./g, '').toLowerCase();
        domain = domain.toLowerCase();

        const normalized = local + domain;
        st.add(normalized);
    }

    return st.size;
}
