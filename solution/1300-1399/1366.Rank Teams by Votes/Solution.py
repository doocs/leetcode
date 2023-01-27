class Solution:
    def rankTeams(self, votes: List[str]) -> str:
        n = len(votes[0])
        cnt = defaultdict(lambda: [0] * n)
        for vote in votes:
            for i, c in enumerate(vote):
                cnt[c][i] += 1
        return "".join(sorted(votes[0], key=lambda x: (cnt[x], -ord(x)), reverse=True))
