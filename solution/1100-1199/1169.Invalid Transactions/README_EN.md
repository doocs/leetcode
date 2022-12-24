# [1169. Invalid Transactions](https://leetcode.com/problems/invalid-transactions)

[中文文档](/solution/1100-1199/1169.Invalid%20Transactions/README.md)

## Description

<p>A transaction is possibly invalid if:</p>

<ul>
	<li>the amount exceeds <code>$1000</code>, or;</li>
	<li>if it occurs within (and including) <code>60</code> minutes of another transaction with the <strong>same name</strong> in a <strong>different city</strong>.</li>
</ul>

<p>You are given an array of strings <code>transaction</code> where <code>transactions[i]</code> consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.</p>

<p>Return a list of <code>transactions</code> that are possibly invalid. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> transactions = [&quot;alice,20,800,mtv&quot;,&quot;alice,50,100,beijing&quot;]
<strong>Output:</strong> [&quot;alice,20,800,mtv&quot;,&quot;alice,50,100,beijing&quot;]
<strong>Explanation:</strong> The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> transactions = [&quot;alice,20,800,mtv&quot;,&quot;alice,50,1200,mtv&quot;]
<strong>Output:</strong> [&quot;alice,50,1200,mtv&quot;]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> transactions = [&quot;alice,20,800,mtv&quot;,&quot;bob,50,1200,mtv&quot;]
<strong>Output:</strong> [&quot;bob,50,1200,mtv&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>transactions.length &lt;= 1000</code></li>
	<li>Each <code>transactions[i]</code> takes the form <code>&quot;{name},{time},{amount},{city}&quot;</code></li>
	<li>Each <code>{name}</code> and <code>{city}</code> consist of lowercase English letters, and have lengths between <code>1</code> and <code>10</code>.</li>
	<li>Each <code>{time}</code> consist of digits, and represent an integer between <code>0</code> and <code>1000</code>.</li>
	<li>Each <code>{amount}</code> consist of digits, and represent an integer between <code>0</code> and <code>2000</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
