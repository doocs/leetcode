class Solution:
    def uniqueEmailGroups(self, emails: list[str]) -> int:
        st = set()
        for email in emails:
            local, domain = email.split("@")
            local = local.split("+")[0].replace(".", "").lower()
            domain = domain.lower()
            normalized = local + domain
            st.add(normalized)
        return len(st)
