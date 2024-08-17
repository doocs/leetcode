class Solution:
    def displayTable(self, orders: List[List[str]]) -> List[List[str]]:
        tables = defaultdict(list)
        items = set()
        for _, table, foodItem in orders:
            tables[int(table)].append(foodItem)
            items.add(foodItem)
        sorted_items = sorted(items)
        ans = [["Table"] + sorted_items]
        for table in sorted(tables):
            cnt = Counter(tables[table])
            row = [str(table)] + [str(cnt[item]) for item in sorted_items]
            ans.append(row)
        return ans
