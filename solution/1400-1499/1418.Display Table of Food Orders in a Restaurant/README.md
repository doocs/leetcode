# [1418. 点菜展示表](https://leetcode.cn/problems/display-table-of-food-orders-in-a-restaurant)

[English Version](/solution/1400-1499/1418.Display%20Table%20of%20Food%20Orders%20in%20a%20Restaurant/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
