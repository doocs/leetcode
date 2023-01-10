class Solution:
    def largestWordCount(self, messages: List[str], senders: List[str]) -> str:
        cnt = Counter()
        for msg, sender in zip(messages, senders):
            cnt[sender] += msg.count(' ') + 1
        ans = ''
        for sender, v in cnt.items():
            if cnt[ans] < v or (cnt[ans] == v and ans < sender):
                ans = sender
        return ans
