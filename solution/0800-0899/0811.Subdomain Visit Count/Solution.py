class Solution:
    def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
        domains = Counter()
        for item in cpdomains:
            count, domain = item.split()
            count = int(count)
            subs = domain.split('.')
            for i in range(len(subs)):
                key = '.'.join(subs[i:])
                domains[key] += count
        return [f'{cnt} {domain}' for domain, cnt in domains.items()]
