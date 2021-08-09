class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        chars = collections.defaultdict(list)
        for s in strs:
            k = ''.join(sorted(list(s)))
            chars[k].append(s)
        return list(chars.values())
