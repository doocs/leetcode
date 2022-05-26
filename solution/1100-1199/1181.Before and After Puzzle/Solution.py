class Solution:
    def beforeAndAfterPuzzles(self, phrases: List[str]) -> List[str]:
        same_first_word = defaultdict(set)
        for i, phrase in enumerate(phrases):
            same_first_word[phrase.split()[0]].add(i)
        res = set()
        for i, phrase in enumerate(phrases):
            words = phrase.split()
            last_word = words[-1]
            if last_word in same_first_word:
                for j in same_first_word[last_word]:
                    if i != j:
                        res.add(' '.join(words[:-1] + phrases[j].split()))
        return sorted(list(res))
