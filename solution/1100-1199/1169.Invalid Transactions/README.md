# [1169. 查询无效交易](https://leetcode.cn/problems/invalid-transactions)

[English Version](/solution/1100-1199/1169.Invalid%20Transactions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果出现下述两种情况，交易 <strong>可能无效</strong>：</p>

<ul>
	<li>交易金额超过<meta charset="UTF-8" />&nbsp;<code>$1000</code></li>
	<li>或者，它和&nbsp;<strong>另一个城市</strong>&nbsp;中 <strong>同名</strong> 的另一笔交易相隔不超过 <code>60</code> 分钟（包含 60 分钟整）</li>
</ul>

<p>给定字符串数组交易清单<meta charset="UTF-8" />&nbsp;<code>transaction</code>&nbsp;。每个交易字符串&nbsp;<code>transactions[i]</code>&nbsp;由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。</p>

<p>返回&nbsp;<code>transactions</code>，返回可能无效的交易列表。你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
<strong>输出：</strong>["alice,20,800,mtv","alice,50,100,beijing"]
<strong>解释：</strong>第一笔交易是无效的，因为第二笔交易和它间隔不超过 60 分钟、名称相同且发生在不同的城市。同样，第二笔交易也是无效的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
<strong>输出：</strong>["alice,50,1200,mtv"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
<strong>输出：</strong>["bob,50,1200,mtv"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>transactions.length &lt;= 1000</code></li>
	<li>每笔交易&nbsp;<code>transactions[i]</code>&nbsp;按&nbsp;<code>"{name},{time},{amount},{city}"</code>&nbsp;的格式进行记录</li>
	<li>每个交易名称&nbsp;<code>{name}</code>&nbsp;和城市&nbsp;<code>{city}</code>&nbsp;都由小写英文字母组成，长度在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>10</code>&nbsp;之间</li>
	<li>每个交易时间&nbsp;<code>{time}</code>&nbsp;由一些数字组成，表示一个&nbsp;<code>0</code>&nbsp;到&nbsp;<code>1000</code>&nbsp;之间的整数</li>
	<li>每笔交易金额&nbsp;<code>{amount}</code>&nbsp;由一些数字组成，表示一个&nbsp;<code>0</code> 到&nbsp;<code>2000</code>&nbsp;之间的整数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 模拟**

遍历交易列表，对于每笔交易，如果金额大于 1000，或者同名且城市不同且时间间隔不超过 60 分钟，则将其加入答案。

具体地，我们使用哈希表 `d` 记录每个交易，其中键为交易名称，值为一个列表，列表中的每个元素为一个三元组 `(time, city, index)`，表示在 `time` 时刻在 `city` 城市进行了编号为 `index` 的交易。同时，我们使用哈希表 `idx` 记录答案中的交易编号。

遍历交易列表，对于每笔交易，我们首先将其加入哈希表 `d` 中，然后判断其金额是否大于 1000，如果是，则将其编号加入答案中。然后我们遍历哈希表 `d` 中的交易，如果交易名称相同且城市不同且时间间隔不超过 60 分钟，则将其编号加入答案中。

最后，我们遍历答案中的交易编号，将其对应的交易加入答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为交易列表的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def invalidTransactions(self, transactions: List[str]) -> List[str]:
        d = defaultdict(list)
        idx = set()
        for i, x in enumerate(transactions):
            name, time, amount, city = x.split(",")
            time, amount = int(time), int(amount)
            d[name].append((time, city, i))
            if amount > 1000:
                idx.add(i)
            for t, c, j in d[name]:
                if c != city and abs(time - t) <= 60:
                    idx.add(i)
                    idx.add(j)
        return [transactions[i] for i in idx]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        Map<String, List<Item>> d = new HashMap<>();
        Set<Integer> idx = new HashSet<>();
        for (int i = 0; i < transactions.length; ++i) {
            var e = transactions[i].split(",");
            String name = e[0];
            int time = Integer.parseInt(e[1]);
            int amount = Integer.parseInt(e[2]);
            String city = e[3];
            d.computeIfAbsent(name, k -> new ArrayList<>()).add(new Item(time, city, i));
            if (amount > 1000) {
                idx.add(i);
            }
            for (Item item : d.get(name)) {
                if (!city.equals(item.city) && Math.abs(time - item.t) <= 60) {
                    idx.add(i);
                    idx.add(item.i);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i : idx) {
            ans.add(transactions[i]);
        }
        return ans;
    }
}

class Item {
    int t;
    String city;
    int i;

    Item(int t, String city, int i) {
        this.t = t;
        this.city = city;
        this.i = i;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> invalidTransactions(vector<string>& transactions) {
        unordered_map<string, vector<tuple<int, string, int>>> d;
        unordered_set<int> idx;
        for (int i = 0; i < transactions.size(); ++i) {
            vector<string> e = split(transactions[i], ',');
            string name = e[0];
            int time = stoi(e[1]);
            int amount = stoi(e[2]);
            string city = e[3];
            d[name].push_back({time, city, i});
            if (amount > 1000) {
                idx.insert(i);
            }
            for (auto& [t, c, j] : d[name]) {
                if (c != city && abs(time - t) <= 60) {
                    idx.insert(i);
                    idx.insert(j);
                }
            }
        }
        vector<string> ans;
        for (int i : idx) {
            ans.emplace_back(transactions[i]);
        }
        return ans;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};
```

### **Go**

```go
func invalidTransactions(transactions []string) (ans []string) {
	d := map[string][]tuple{}
	idx := map[int]bool{}
	for i, x := range transactions {
		e := strings.Split(x, ",")
		name := e[0]
		time, _ := strconv.Atoi(e[1])
		amount, _ := strconv.Atoi(e[2])
		city := e[3]
		d[name] = append(d[name], tuple{time, city, i})
		if amount > 1000 {
			idx[i] = true
		}
		for _, item := range d[name] {
			if city != item.city && abs(time-item.t) <= 60 {
				idx[i], idx[item.i] = true, true
			}
		}
	}
	for i := range idx {
		ans = append(ans, transactions[i])
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

type tuple struct {
	t    int
	city string
	i    int
}
```

### **...**

```

```

<!-- tabs:end -->
