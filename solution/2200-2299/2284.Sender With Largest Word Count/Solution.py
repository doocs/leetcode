class Solution:
    def largestWordCount(self, messages: List[str], senders: List[str]) -> str:
        cnt = Counter()
        for message, sender in zip(messages, senders):
            cnt[sender] += message.count(" ") + 1
        ans = senders[0]
        for k, v in cnt.items():
            if cnt[ans] < v or (cnt[ans] == v and ans < k):
                ans = k
        return ans
