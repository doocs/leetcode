class Solution:
    def numUniqueEmails(self, emails: List[str]) -> int:
        s = set()
        for email in emails:
            local, domain = email.split('@')
            local = local.replace('.', '')
            if (i := local.find('+')) != -1:
                local = local[:i]
            s.add(local + '@' + domain)
        return len(s)
