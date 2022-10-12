# [1418. Display Table of Food Orders in a Restaurant](https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant)

[中文文档](/solution/1400-1499/1418.Display%20Table%20of%20Food%20Orders%20in%20a%20Restaurant/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def displayTable(self, orders: List[List[str]]) -> List[List[str]]:
        tables = set()
        foods = set()
        mp = Counter()
        for _, table, food in orders:
            tables.add(int(table))
            foods.add(food)
            mp[f'{table}.{food}'] += 1
        foods = sorted(list(foods))
        tables = sorted(list(tables))
        res = [['Table'] + foods]
        for table in tables:
            t = [str(table)]
            for food in foods:
                t.append(str(mp[f'{table}.{food}']))
            res.append(t)
        return res
```

### **Java**

```java
class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<Integer> tables = new HashSet<>();
        Set<String> foods = new HashSet<>();
        Map<String, Integer> mp = new HashMap<>();
        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            tables.add(table);
            foods.add(food);
            String key = table + "." + food;
            mp.put(key, mp.getOrDefault(key, 0) + 1);
        }
        List<Integer> t = new ArrayList<>(tables);
        List<String> f = new ArrayList<>(foods);
        Collections.sort(t);
        Collections.sort(f);
        List<List<String>> res = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(f);
        res.add(title);
        for (int table : t) {
            List<String> tmp = new ArrayList<>();
            tmp.add(String.valueOf(table));
            for (String food : f) {
                tmp.add(String.valueOf(mp.getOrDefault(table + "." + food, 0)));
            }
            res.add(tmp);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> displayTable(vector<vector<string>>& orders) {
        unordered_set<int> tables;
        unordered_set<string> foods;
        unordered_map<string, int> mp;
        for (auto& order : orders) {
            int table = stoi(order[1]);
            string food = order[2];
            tables.insert(table);
            foods.insert(food);
            ++mp[order[1] + "." + food];
        }
        vector<int> t;
        t.assign(tables.begin(), tables.end());
        sort(t.begin(), t.end());
        vector<string> f;
        f.assign(foods.begin(), foods.end());
        sort(f.begin(), f.end());
        vector<vector<string>> res;
        vector<string> title;
        title.push_back("Table");
        for (auto e : f) title.push_back(e);
        res.push_back(title);
        for (int table : t) {
            vector<string> tmp;
            tmp.push_back(to_string(table));
            for (string food : f) {
                tmp.push_back(to_string(mp[to_string(table) + "." + food]));
            }
            res.push_back(tmp);
        }
        return res;
    }
};
```

### **Go**

```go
func displayTable(orders [][]string) [][]string {
	tables := make(map[int]bool)
	foods := make(map[string]bool)
	mp := make(map[string]int)
	for _, order := range orders {
		table, food := order[1], order[2]
		t, _ := strconv.Atoi(table)
		tables[t] = true
		foods[food] = true
		key := table + "." + food
		mp[key] += 1
	}
	var t []int
	var f []string
	for i := range tables {
		t = append(t, i)
	}
	for i := range foods {
		f = append(f, i)
	}
	sort.Ints(t)
	sort.Strings(f)
	var res [][]string
	var title []string
	title = append(title, "Table")
	for _, e := range f {
		title = append(title, e)
	}
	res = append(res, title)
	for _, table := range t {
		var tmp []string
		tmp = append(tmp, strconv.Itoa(table))
		for _, food := range f {
			tmp = append(tmp, strconv.Itoa(mp[strconv.Itoa(table)+"."+food]))
		}
		res = append(res, tmp)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
