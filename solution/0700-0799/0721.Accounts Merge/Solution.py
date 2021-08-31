class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        n = len(accounts)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        email_id = {}
        for i in range(n):
            name = accounts[i][0]
            for email in accounts[i][1:]:
                if email in email_id:
                    p[find(i)] = find(email_id[email])
                else:
                    email_id[email] = i

        mp = collections.defaultdict(set)
        for i in range(n):
            pa = find(i)
            for email in accounts[i][1:]:
                mp[pa].add(email)
        res = []
        for i, emails in mp.items():
            t = [accounts[i][0]]
            t.extend(sorted(emails))
            res.append(t)
        return res
