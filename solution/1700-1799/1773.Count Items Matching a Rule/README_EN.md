# [1773. Count Items Matching a Rule](https://leetcode.com/problems/count-items-matching-a-rule)

[中文文档](/solution/1700-1799/1773.Count%20Items%20Matching%20a%20Rule/README.md)

## Description

<p>You are given an array <code>items</code>, where each <code>items[i] = [type<sub>i</sub>, color<sub>i</sub>, name<sub>i</sub>]</code> describes the type, color, and name of the <code>i<sup>th</sup></code> item. You are also given a rule represented by two strings, <code>ruleKey</code> and <code>ruleValue</code>.</p>

<p>The <code>i<sup>th</sup></code> item is said to match the rule if <strong>one</strong> of the following is true:</p>

<ul>
	<li><code>ruleKey == &quot;type&quot;</code> and <code>ruleValue == type<sub>i</sub></code>.</li>
	<li><code>ruleKey == &quot;color&quot;</code> and <code>ruleValue == color<sub>i</sub></code>.</li>
	<li><code>ruleKey == &quot;name&quot;</code> and <code>ruleValue == name<sub>i</sub></code>.</li>
</ul>

<p>Return <em>the number of items that match the given rule</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[&quot;phone&quot;,&quot;blue&quot;,&quot;pixel&quot;],[&quot;computer&quot;,&quot;silver&quot;,&quot;lenovo&quot;],[&quot;phone&quot;,&quot;gold&quot;,&quot;iphone&quot;]], ruleKey = &quot;color&quot;, ruleValue = &quot;silver&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one item matching the given rule, which is [&quot;computer&quot;,&quot;silver&quot;,&quot;lenovo&quot;].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[&quot;phone&quot;,&quot;blue&quot;,&quot;pixel&quot;],[&quot;computer&quot;,&quot;silver&quot;,&quot;phone&quot;],[&quot;phone&quot;,&quot;gold&quot;,&quot;iphone&quot;]], ruleKey = &quot;type&quot;, ruleValue = &quot;phone&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are only two items matching the given rule, which are [&quot;phone&quot;,&quot;blue&quot;,&quot;pixel&quot;] and [&quot;phone&quot;,&quot;gold&quot;,&quot;iphone&quot;]. Note that the item [&quot;computer&quot;,&quot;silver&quot;,&quot;phone&quot;] does not match.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= type<sub>i</sub>.length, color<sub>i</sub>.length, name<sub>i</sub>.length, ruleValue.length &lt;= 10</code></li>
	<li><code>ruleKey</code> is equal to either <code>&quot;type&quot;</code>, <code>&quot;color&quot;</code>, or <code>&quot;name&quot;</code>.</li>
	<li>All strings consist only of lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countMatches(self, items: List[List[str]], ruleKey: str, ruleValue: str) -> int:
        i = 0 if ruleKey[0] == 't' else (1 if ruleKey[0] == 'c' else 2)
        return sum(v[i] == ruleValue for v in items)
```

### **Java**

```java
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int i = ruleKey.charAt(0) == 't' ? 0 : (ruleKey.charAt(0) == 'c' ? 1 : 2);
        int ans = 0;
        for (var v : items) {
            if (v.get(i).equals(ruleValue)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countMatches(vector<vector<string>>& items, string ruleKey, string ruleValue) {
        int i = ruleKey[0] == 't' ? 0 : (ruleKey[0] == 'c' ? 1 : 2);
        return count_if(items.begin(), items.end(), [&](auto& v) { return v[i] == ruleValue; });
    }
};
```

### **Go**

```go
func countMatches(items [][]string, ruleKey string, ruleValue string) (ans int) {
	i := map[byte]int{'t': 0, 'c': 1, 'n': 2}[ruleKey[0]]
	for _, v := range items {
		if v[i] == ruleValue {
			ans++
		}
	}
	return
}
```

### **C**

```c
int countMatches(char ***items, int itemsSize, int *itemsColSize, char *ruleKey, char *ruleValue) {
    int k = strcmp(ruleKey, "type") == 0 ? 0 : strcmp(ruleKey, "color") == 0 ? 1 : 2;
    int res = 0;
    for (int i = 0; i < itemsSize; i++) {
        if (strcmp(items[i][k], ruleValue) == 0) {
            res++;
        }
    }
    return res;
}
```

### **TypeScript**

```ts
function countMatches(
    items: string[][],
    ruleKey: string,
    ruleValue: string,
): number {
    const key = ruleKey === 'type' ? 0 : ruleKey === 'color' ? 1 : 2;
    return items.reduce((r, v) => r + (v[key] === ruleValue ? 1 : 0), 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_matches(items: Vec<Vec<String>>, rule_key: String, rule_value: String) -> i32 {
        let key = if rule_key == "type" {
            0
        } else if rule_key == "color" {
            1
        } else {
            2
        };
        items.iter().filter(|v| v[key] == rule_value).count() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
