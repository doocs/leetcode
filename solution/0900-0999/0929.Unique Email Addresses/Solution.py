class Solution:
    def numUniqueEmails(self, emails):
        """
        :type emails: List[str]
        :rtype: int
        """
        E = {}
        for each in emails:
            local_name, domain = each.split('@')
            local_name = local_name.split('+')[0].replace('.', '')
            E[local_name + '@' + domain] = 0

        return len(E)
