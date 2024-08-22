---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1418.Display%20Table%20of%20Food%20Orders%20in%20a%20Restaurant/README.md
rating: 1485
source: 第 185 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 字符串
    - 有序集合
    - 排序
---

<!-- problem:start -->

# [1418. 点菜展示表](https://leetcode.cn/problems/display-table-of-food-orders-in-a-restaurant)

[English Version](/solution/1400-1499/1418.Display%20Table%20of%20Food%20Orders%20in%20a%20Restaurant/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>orders</code>，表示客户在餐厅中完成的订单，确切地说， <code>orders[i]=[customerName<sub>i</sub>,tableNumber<sub>i</sub>,foodItem<sub>i</sub>]</code> ，其中 <code>customerName<sub>i</sub></code> 是客户的姓名，<code>tableNumber<sub>i</sub></code> 是客户所在餐桌的桌号，而 <code>foodItem<sub>i</sub></code> 是客户点的餐品名称。</p>

<p>请你返回该餐厅的 <strong>点菜展示表</strong><em> 。</em>在这张表中，表中第一行为标题，其第一列为餐桌桌号 &ldquo;Table&rdquo; ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。</p>

<p>注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>orders = [[&quot;David&quot;,&quot;3&quot;,&quot;Ceviche&quot;],[&quot;Corina&quot;,&quot;10&quot;,&quot;Beef Burrito&quot;],[&quot;David&quot;,&quot;3&quot;,&quot;Fried Chicken&quot;],[&quot;Carla&quot;,&quot;5&quot;,&quot;Water&quot;],[&quot;Carla&quot;,&quot;5&quot;,&quot;Ceviche&quot;],[&quot;Rous&quot;,&quot;3&quot;,&quot;Ceviche&quot;]]
<strong>输出：</strong>[[&quot;Table&quot;,&quot;Beef Burrito&quot;,&quot;Ceviche&quot;,&quot;Fried Chicken&quot;,&quot;Water&quot;],[&quot;3&quot;,&quot;0&quot;,&quot;2&quot;,&quot;1&quot;,&quot;0&quot;],[&quot;5&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;1&quot;],[&quot;10&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;]] 
<strong>解释：
</strong>点菜展示表如下所示：
<strong>Table,Beef Burrito,Ceviche,Fried Chicken,Water</strong>
3    ,0           ,2      ,1            ,0
5    ,0           ,1      ,0            ,1
10   ,1           ,0      ,0            ,0
对于餐桌 3：David 点了 &quot;Ceviche&quot; 和 &quot;Fried Chicken&quot;，而 Rous 点了 &quot;Ceviche&quot;
而餐桌 5：Carla 点了 &quot;Water&quot; 和 &quot;Ceviche&quot;
餐桌 10：Corina 点了 &quot;Beef Burrito&quot; 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>orders = [[&quot;James&quot;,&quot;12&quot;,&quot;Fried Chicken&quot;],[&quot;Ratesh&quot;,&quot;12&quot;,&quot;Fried Chicken&quot;],[&quot;Amadeus&quot;,&quot;12&quot;,&quot;Fried Chicken&quot;],[&quot;Adam&quot;,&quot;1&quot;,&quot;Canadian Waffles&quot;],[&quot;Brianna&quot;,&quot;1&quot;,&quot;Canadian Waffles&quot;]]
<strong>输出：</strong>[[&quot;Table&quot;,&quot;Canadian Waffles&quot;,&quot;Fried Chicken&quot;],[&quot;1&quot;,&quot;2&quot;,&quot;0&quot;],[&quot;12&quot;,&quot;0&quot;,&quot;3&quot;]] 
<strong>解释：</strong>
对于餐桌 1：Adam 和 Brianna 都点了 &quot;Canadian Waffles&quot;
而餐桌 12：James, Ratesh 和 Amadeus 都点了 &quot;Fried Chicken&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>orders = [[&quot;Laura&quot;,&quot;2&quot;,&quot;Bean Burrito&quot;],[&quot;Jhon&quot;,&quot;2&quot;,&quot;Beef Burrito&quot;],[&quot;Melissa&quot;,&quot;2&quot;,&quot;Soda&quot;]]
<strong>输出：</strong>[[&quot;Table&quot;,&quot;Bean Burrito&quot;,&quot;Beef Burrito&quot;,&quot;Soda&quot;],[&quot;2&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;orders.length &lt;= 5 * 10^4</code></li>
	<li><code>orders[i].length == 3</code></li>
	<li><code>1 &lt;= customerName<sub>i</sub>.length, foodItem<sub>i</sub>.length &lt;= 20</code></li>
	<li><code>customerName<sub>i</sub></code> 和 <code>foodItem<sub>i</sub></code> 由大小写英文字母及空格字符 <code>&#39; &#39;</code> 组成。</li>
	<li><code>tableNumber<sub>i</sub></code> 是 <code>1</code> 到 <code>500</code> 范围内的整数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序

我们可以用一个哈希表 $\textit{tables}$ 来存储每张餐桌点的菜品，用一个集合 $\textit{items}$ 来存储所有的菜品。

遍历 $\textit{orders}$，将每张餐桌点的菜品存入 $\textit{tables}$ 和 $\textit{items}$ 中。

然后我们将 $\textit{items}$ 排序，得到 $\textit{sortedItems}$。

接下来，我们构建答案数组 $\textit{ans}$，首先将标题行 $\textit{header}$ 加入 $\textit{ans}$，然后遍历排序后的 $\textit{tables}$，对于每张餐桌，我们用一个计数器 $\textit{cnt}$ 来统计每种菜品的数量，然后构建一行 $\textit{row}$，将其加入 $\textit{ans}$。

最后返回 $\textit{ans}$。

时间复杂度 $O(n + m \times \log m + k \times \log k + m \times k)$，空间复杂度 $O(n + m + k)$。其中 $n$ 是数组 $\textit{orders}$ 的长度，而 $m$ 和 $k$ 分别表示菜品种类数和餐桌数。

<!-- tabs:start -->

#### Python3

```python
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
```

#### Java

```java
class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        TreeMap<Integer, List<String>> tables = new TreeMap<>();
        Set<String> items = new HashSet<>();
        for (List<String> o : orders) {
            int table = Integer.parseInt(o.get(1));
            String foodItem = o.get(2);
            tables.computeIfAbsent(table, k -> new ArrayList<>()).add(foodItem);
            items.add(foodItem);
        }
        List<String> sortedItems = new ArrayList<>(items);
        Collections.sort(sortedItems);
        List<List<String>> ans = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(sortedItems);
        ans.add(header);
        for (Map.Entry<Integer, List<String>> entry : tables.entrySet()) {
            Map<String, Integer> cnt = new HashMap<>();
            for (String item : entry.getValue()) {
                cnt.merge(item, 1, Integer::sum);
            }
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(entry.getKey()));
            for (String item : sortedItems) {
                row.add(String.valueOf(cnt.getOrDefault(item, 0)));
            }
            ans.add(row);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> displayTable(vector<vector<string>>& orders) {
        map<int, vector<string>> tables;
        set<string> sortedItems;
        for (auto& o : orders) {
            int table = stoi(o[1]);
            string foodItem = o[2];
            tables[table].push_back(foodItem);
            sortedItems.insert(foodItem);
        }
        vector<vector<string>> ans;
        vector<string> header = {"Table"};
        header.insert(header.end(), sortedItems.begin(), sortedItems.end());
        ans.push_back(header);
        for (auto& [table, items] : tables) {
            unordered_map<string, int> cnt;
            for (string& item : items) {
                cnt[item]++;
            }
            vector<string> row;
            row.push_back(to_string(table));
            for (const string& item : sortedItems) {
                row.push_back(to_string(cnt[item]));
            }
            ans.push_back(row);
        }
        return ans;
    }
};
```

#### Go

```go
func displayTable(orders [][]string) [][]string {
	tables := make(map[int]map[string]int)
	items := make(map[string]bool)
	for _, order := range orders {
		table, _ := strconv.Atoi(order[1])
		foodItem := order[2]
		if tables[table] == nil {
			tables[table] = make(map[string]int)
		}
		tables[table][foodItem]++
		items[foodItem] = true
	}
	sortedItems := make([]string, 0, len(items))
	for item := range items {
		sortedItems = append(sortedItems, item)
	}
	sort.Strings(sortedItems)
	ans := [][]string{}
	header := append([]string{"Table"}, sortedItems...)
	ans = append(ans, header)
	tableNums := make([]int, 0, len(tables))
	for table := range tables {
		tableNums = append(tableNums, table)
	}
	sort.Ints(tableNums)
	for _, table := range tableNums {
		row := []string{strconv.Itoa(table)}
		for _, item := range sortedItems {
			count := tables[table][item]
			row = append(row, strconv.Itoa(count))
		}
		ans = append(ans, row)
	}
	return ans
}
```

#### TypeScript

```ts
function displayTable(orders: string[][]): string[][] {
    const tables: Record<number, Record<string, number>> = {};
    const items: Set<string> = new Set();
    for (const [_, table, foodItem] of orders) {
        const t = +table;
        if (!tables[t]) {
            tables[t] = {};
        }
        if (!tables[t][foodItem]) {
            tables[t][foodItem] = 0;
        }
        tables[t][foodItem]++;
        items.add(foodItem);
    }
    const sortedItems = Array.from(items).sort();
    const ans: string[][] = [];
    const header: string[] = ['Table', ...sortedItems];
    ans.push(header);
    const sortedTableNumbers = Object.keys(tables)
        .map(Number)
        .sort((a, b) => a - b);
    for (const table of sortedTableNumbers) {
        const row: string[] = [table.toString()];
        for (const item of sortedItems) {
            row.push((tables[table][item] || 0).toString());
        }
        ans.push(row);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
