# [599. 两个列表的最小索引总和](https://leetcode.cn/problems/minimum-index-sum-of-two-lists)

[English Version](/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。</p>

<p>你需要帮助他们用<strong>最少的索引和</strong>找出他们<strong>共同喜爱的餐厅</strong>。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
<strong>输出:</strong> ["Shogun"]
<strong>解释:</strong> 他们唯一共同喜爱的餐厅是“Shogun”。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
<strong>输出:</strong> ["Shogun"]
<strong>解释:</strong> 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code>&nbsp;</li>
	<li><code>list1[i]</code> 和 <code>list2[i]</code> 由空格<meta charset="UTF-8" />&nbsp;<code>' '</code>&nbsp;和英文字母组成。</li>
	<li><code>list1</code> 的所有字符串都是 <strong>唯一</strong> 的。</li>
	<li><code>list2</code> 中的所有字符串都是 <strong>唯一</strong> 的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先用哈希表 mp 记录 list2 的每个字符串以及对应的下标。初始化最小的索引和 mi = 2000，ans 表示结果列表，初始值为空。

遍历 list1 每个字符串 v，若 v 在 mp 中，则计算两个字符串的索引和 t，并更新 ans 和 mi。

最后返回 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
