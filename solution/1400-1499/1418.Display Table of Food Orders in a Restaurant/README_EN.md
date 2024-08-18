---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1418.Display%20Table%20of%20Food%20Orders%20in%20a%20Restaurant/README_EN.md
rating: 1485
source: Weekly Contest 185 Q2
tags:
    - Array
    - Hash Table
    - String
    - Ordered Set
    - Sorting
---

<!-- problem:start -->

# [1418. Display Table of Food Orders in a Restaurant](https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant)

[中文文档](/solution/1400-1499/1418.Display%20Table%20of%20Food%20Orders%20in%20a%20Restaurant/README.md)

## Description

<!-- description:start -->

<p>Given&nbsp;the array <code>orders</code>, which represents the orders that customers have done in a restaurant. More specifically&nbsp;<code>orders[i]=[customerName<sub>i</sub>,tableNumber<sub>i</sub>,foodItem<sub>i</sub>]</code> where <code>customerName<sub>i</sub></code> is the name of the customer, <code>tableNumber<sub>i</sub></code>&nbsp;is the table customer sit at, and <code>foodItem<sub>i</sub></code>&nbsp;is the item customer orders.</p>

<p><em>Return the restaurant&#39;s &ldquo;<strong>display table</strong>&rdquo;</em>. The &ldquo;<strong>display table</strong>&rdquo; is a table whose row entries denote how many of each food item each table ordered. The first column is the table number and the remaining columns correspond to each food item in alphabetical order. The first row should be a header whose first column is &ldquo;Table&rdquo;, followed by the names of the food items. Note that the customer names are not part of the table. Additionally, the rows should be sorted in numerically increasing order.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> orders = [[&quot;David&quot;,&quot;3&quot;,&quot;Ceviche&quot;],[&quot;Corina&quot;,&quot;10&quot;,&quot;Beef Burrito&quot;],[&quot;David&quot;,&quot;3&quot;,&quot;Fried Chicken&quot;],[&quot;Carla&quot;,&quot;5&quot;,&quot;Water&quot;],[&quot;Carla&quot;,&quot;5&quot;,&quot;Ceviche&quot;],[&quot;Rous&quot;,&quot;3&quot;,&quot;Ceviche&quot;]]

<strong>Output:</strong> [[&quot;Table&quot;,&quot;Beef Burrito&quot;,&quot;Ceviche&quot;,&quot;Fried Chicken&quot;,&quot;Water&quot;],[&quot;3&quot;,&quot;0&quot;,&quot;2&quot;,&quot;1&quot;,&quot;0&quot;],[&quot;5&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;1&quot;],[&quot;10&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;]] 

<strong>Explanation:

</strong>The displaying table looks like:

<strong>Table,Beef Burrito,Ceviche,Fried Chicken,Water</strong>

3    ,0           ,2      ,1            ,0

5    ,0           ,1      ,0            ,1

10   ,1           ,0      ,0            ,0

For the table 3: David orders &quot;Ceviche&quot; and &quot;Fried Chicken&quot;, and Rous orders &quot;Ceviche&quot;.

For the table 5: Carla orders &quot;Water&quot; and &quot;Ceviche&quot;.

For the table 10: Corina orders &quot;Beef Burrito&quot;. 

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> orders = [[&quot;James&quot;,&quot;12&quot;,&quot;Fried Chicken&quot;],[&quot;Ratesh&quot;,&quot;12&quot;,&quot;Fried Chicken&quot;],[&quot;Amadeus&quot;,&quot;12&quot;,&quot;Fried Chicken&quot;],[&quot;Adam&quot;,&quot;1&quot;,&quot;Canadian Waffles&quot;],[&quot;Brianna&quot;,&quot;1&quot;,&quot;Canadian Waffles&quot;]]

<strong>Output:</strong> [[&quot;Table&quot;,&quot;Canadian Waffles&quot;,&quot;Fried Chicken&quot;],[&quot;1&quot;,&quot;2&quot;,&quot;0&quot;],[&quot;12&quot;,&quot;0&quot;,&quot;3&quot;]] 

<strong>Explanation:</strong> 

For the table 1: Adam and Brianna order &quot;Canadian Waffles&quot;.

For the table 12: James, Ratesh and Amadeus order &quot;Fried Chicken&quot;.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> orders = [[&quot;Laura&quot;,&quot;2&quot;,&quot;Bean Burrito&quot;],[&quot;Jhon&quot;,&quot;2&quot;,&quot;Beef Burrito&quot;],[&quot;Melissa&quot;,&quot;2&quot;,&quot;Soda&quot;]]

<strong>Output:</strong> [[&quot;Table&quot;,&quot;Bean Burrito&quot;,&quot;Beef Burrito&quot;,&quot;Soda&quot;],[&quot;2&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;]]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;=&nbsp;orders.length &lt;= 5 * 10^4</code></li>

    <li><code>orders[i].length == 3</code></li>

    <li><code>1 &lt;= customerName<sub>i</sub>.length, foodItem<sub>i</sub>.length &lt;= 20</code></li>

    <li><code>customerName<sub>i</sub></code> and <code>foodItem<sub>i</sub></code> consist of lowercase and uppercase English letters and the space character.</li>

    <li><code>tableNumber<sub>i</sub>&nbsp;</code>is a valid integer between <code>1</code> and <code>500</code>.</li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sorting

We can use a hash table $\textit{tables}$ to store the dishes ordered at each table, and a set $\textit{items}$ to store all the dishes.

Traverse $\textit{orders}$, storing the dishes ordered at each table in $\textit{tables}$ and $\textit{items}$.

Then we sort $\textit{items}$ to get $\textit{sortedItems}$.

Next, we construct the answer array $\textit{ans}$. First, add the header row $\textit{header}$ to $\textit{ans}$. Then, traverse the sorted $\textit{tables}$. For each table, use a counter $\textit{cnt}$ to count the number of each dish, then construct a row $\textit{row}$ and add it to $\textit{ans}$.

Finally, return $\textit{ans}$.

The time complexity is $O(n + m \times \log m + k \times \log k + m \times k)$, and the space complexity is $O(n + m + k)$. Here, $n$ is the length of the array $\textit{orders}$, while $m$ and $k$ represent the number of dish types and the number of tables, respectively.

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
