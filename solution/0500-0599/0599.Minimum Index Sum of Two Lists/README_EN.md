# [599. Minimum Index Sum of Two Lists](https://leetcode.com/problems/minimum-index-sum-of-two-lists)

[中文文档](/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README.md)

## Description

<p>Given two arrays of strings <code>list1</code> and <code>list2</code>, find the <strong>common strings with the least index sum</strong>.</p>

<p>A <strong>common string</strong> is a string that appeared in both <code>list1</code> and <code>list2</code>.</p>

<p>A <strong>common string with the least index sum</strong> is a common string such that if it appeared at <code>list1[i]</code> and <code>list2[j]</code> then <code>i + j</code> should be the minimum value among all the other <strong>common strings</strong>.</p>

<p>Return <em>all the <strong>common strings with the least index sum</strong></em>. Return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;Shogun&quot;,&quot;Tapioca Express&quot;,&quot;Burger King&quot;,&quot;KFC&quot;], list2 = [&quot;Piatti&quot;,&quot;The Grill at Torrey Pines&quot;,&quot;Hungry Hunter Steakhouse&quot;,&quot;Shogun&quot;]
<strong>Output:</strong> [&quot;Shogun&quot;]
<strong>Explanation:</strong> The only common string is &quot;Shogun&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;Shogun&quot;,&quot;Tapioca Express&quot;,&quot;Burger King&quot;,&quot;KFC&quot;], list2 = [&quot;KFC&quot;,&quot;Shogun&quot;,&quot;Burger King&quot;]
<strong>Output:</strong> [&quot;Shogun&quot;]
<strong>Explanation:</strong> The common string with the least index sum is &quot;Shogun&quot; with index sum = (0 + 1) = 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;happy&quot;,&quot;sad&quot;,&quot;good&quot;], list2 = [&quot;sad&quot;,&quot;happy&quot;,&quot;good&quot;]
<strong>Output:</strong> [&quot;sad&quot;,&quot;happy&quot;]
<strong>Explanation:</strong> There are three common strings:
&quot;happy&quot; with index sum = (0 + 1) = 1.
&quot;sad&quot; with index sum = (1 + 0) = 1.
&quot;good&quot; with index sum = (2 + 2) = 4.
The strings with the least index sum are &quot;sad&quot; and &quot;happy&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code></li>
	<li><code>list1[i]</code> and <code>list2[i]</code> consist of spaces <code>&#39; &#39;</code> and English letters.</li>
	<li>All the strings of <code>list1</code> are <strong>unique</strong>.</li>
	<li>All the strings of <code>list2</code> are <strong>unique</strong>.</li>
	<li>There is at least a common string between <code>list1</code> and <code>list2</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRestaurant(self, list1: List[str], list2: List[str]) -> List[str]:
        ans = []
        mp = {v: i for i, v in enumerate(list2)}
        mi = 2000
        for i, v in enumerate(list1):
            if v in mp:
                t = i + mp[v]
                if t < mi:
                    mi = t
                    ans = [v]
                elif t == mi:
                    ans.append(v)
        return ans
```

### **Java**

```java
class Solution {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < list2.length; ++i) {
            mp.put(list2[i], i);
        }
        List<String> ans = new ArrayList<>();
        int mi = 2000;
        for (int i = 0; i < list1.length; ++i) {
            if (mp.containsKey(list1[i])) {
                int t = i + mp.get(list1[i]);
                if (t < mi) {
                    ans = new ArrayList<>();
                    ans.add(list1[i]);
                    mi = t;
                } else if (t == mi) {
                    ans.add(list1[i]);
                }
            }
        }
        return ans.toArray(new String[0]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
        unordered_map<string, int> mp;
        for (int i = 0; i < list2.size(); ++i) mp[list2[i]] = i;
        int mi = 2000;
        vector<string> ans;
        for (int i = 0; i < list1.size(); ++i) {
            if (mp.count(list1[i])) {
                int t = i + mp[list1[i]];
                if (t < mi) {
                    ans.clear();
                    ans.push_back(list1[i]);
                    mi = t;
                } else if (t == mi) {
                    ans.push_back(list1[i]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```cpp
func findRestaurant(list1 []string, list2 []string) []string {
	mp := make(map[string]int)
	for i, v := range list2 {
		mp[v] = i
	}
	mi := 2000
	var ans []string
	for i, v := range list1 {
		if _, ok := mp[v]; ok {
			t := i + mp[v]
			if t < mi {
				ans = []string{v}
				mi = t
			} else if t == mi {
				ans = append(ans, v)
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findRestaurant(list1: string[], list2: string[]): string[] {
    let minI = Infinity;
    const res = [];
    const map = new Map<string, number>(list1.map((s, i) => [s, i]));
    list2.forEach((s, i) => {
        if (map.has(s)) {
            const sumI = i + map.get(s);
            if (sumI <= minI) {
                if (sumI < minI) {
                    minI = sumI;
                    res.length = 0;
                }
                res.push(s);
            }
        }
    });
    return res;
}
```

### **Rust**

```rust
use std::collections::HashMap;
use std::iter::FromIterator;

impl Solution {
    pub fn find_restaurant(list1: Vec<String>, list2: Vec<String>) -> Vec<String> {
        let map: HashMap<String, usize> = HashMap::from_iter(list1.into_iter().zip(0..));
        let mut res = vec![];
        let mut min_i = usize::MAX;
        list2.into_iter().enumerate().for_each(|(i, key)| {
            if map.contains_key(&key) {
                let sum_i = map.get(&key).unwrap() + i;
                if sum_i <= min_i {
                    if (sum_i < min_i) {
                        min_i = sum_i;
                        res.clear();
                    }
                    res.push(key);
                }
            }
        });
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
