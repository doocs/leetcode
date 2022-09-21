class Solution:
    def rearrangeBarcodes(self, barcodes: List[int]) -> List[int]:
        cnt = Counter(barcodes)
        h = [(-v, k) for k, v in cnt.items()]
        heapify(h)
        q = deque()
        ans = []
        while h:
            v, k = heappop(h)
            ans.append(k)
            q.append((v + 1, k))
            while len(q) > 1:
                p = q.popleft()
                if p[0]:
                    heappush(h, p)
        return ans
