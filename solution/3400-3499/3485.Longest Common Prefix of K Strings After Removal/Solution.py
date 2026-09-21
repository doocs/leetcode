class Solution:
    def longestCommonPrefix(self, words: List[str], k: int) -> List[int]:
        n = len(words)
        ans = [0] * n
        if n - 1 < k:
            return ans

        trie = [{'count': 0, 'depth': 0, 'children': [-1] * 26}]
        for word in words:
            cur = 0
            for c in word:
                idx = ord(c) - 97
                if trie[cur]['children'][idx] == -1:
                    trie[cur]['children'][idx] = len(trie)
                    trie.append(
                        {
                            'count': 0,
                            'depth': trie[cur]['depth'] + 1,
                            'children': [-1] * 26,
                        }
                    )
                cur = trie[cur]['children'][idx]
                trie[cur]['count'] += 1

        max_depth = 0
        for i in range(1, len(trie)):
            if trie[i]['count'] >= k:
                max_depth = max(max_depth, trie[i]['depth'])

        global_count = [0] * (max_depth + 1)
        for i in range(1, len(trie)):
            node = trie[i]
            if node['count'] >= k and node['depth'] <= max_depth:
                global_count[node['depth']] += 1

        fragile_list = [[] for _ in range(n)]
        for i, word in enumerate(words):
            cur = 0
            for c in word:
                idx = ord(c) - 97
                cur = trie[cur]['children'][idx]
                if trie[cur]['count'] == k:
                    fragile_list[i].append(trie[cur]['depth'])

        seg_size = max_depth
        if seg_size < 1:
            return ans

        tree = [-1] * (4 * (seg_size + 1))

        def build(idx: int, l: int, r: int) -> None:
            if l == r:
                tree[idx] = l if global_count[l] > 0 else -1
                return
            mid = (l + r) // 2
            build(idx * 2, l, mid)
            build(idx * 2 + 1, mid + 1, r)
            tree[idx] = max(tree[idx * 2], tree[idx * 2 + 1])

        def update(idx: int, l: int, r: int, pos: int, new_val: int) -> None:
            if l == r:
                tree[idx] = l if new_val > 0 else -1
                return
            mid = (l + r) // 2
            if pos <= mid:
                update(idx * 2, l, mid, pos, new_val)
            else:
                update(idx * 2 + 1, mid + 1, r, pos, new_val)
            tree[idx] = max(tree[idx * 2], tree[idx * 2 + 1])

        build(1, 1, seg_size)
        for i in range(n):
            for d in fragile_list[i]:
                update(1, 1, seg_size, d, global_count[d] - 1)
            res = tree[1]
            ans[i] = 0 if res == -1 else res
            for d in fragile_list[i]:
                update(1, 1, seg_size, d, global_count[d])
        return ans
