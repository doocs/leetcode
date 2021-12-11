class Solution:
    def numUniqueEmails(self, emails: List[str]) -> int:
        ans = 0
        s = set()
        for email in emails:
            local, domain = email.split('@')
            local = local.replace('.', '')
            if '+' in local:
                local = local[:local.find('+')]
            s.add(local + '@' + domain)
        return len(s)
