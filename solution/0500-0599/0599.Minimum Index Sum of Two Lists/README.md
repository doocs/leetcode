---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [599. 两个列表的最小索引总和](https://leetcode.cn/problems/minimum-index-sum-of-two-lists)

[English Version](/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个字符串数组&nbsp;<code>list1</code> 和 <code>list2</code>，找到 <strong>索引和最小的公共字符串</strong>。</p>

<p><strong>公共字符串</strong>&nbsp;是同时出现在&nbsp;<code>list1</code> 和 <code>list2</code>&nbsp;中的字符串。</p>

<p>具有 <strong>最小索引和的公共字符串</strong> 是指，如果它在 <code>list1[i]</code> 和 <code>list2[j]</code> 中出现，那么 <code>i + j</code> 应该是所有其他 <strong>公共字符串</strong> 中的最小值。</p>

<p>返回所有 <strong>具有最小索引和的公共字符串</strong>。以 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
<strong>输出:</strong> ["Shogun"]
<strong>解释:</strong> 唯一的公共字符串是 “Shogun”。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
<strong>输出:</strong> ["Shogun"]
<strong>解释:</strong> 具有最小索引和的公共字符串是 “Shogun”，它有最小的索引和 = (0 + 1) = 1。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>list1 = ["happy","sad","good"], list2 = ["sad","happy","good"]
<b>输出：</b>["sad","happy"]
<b>解释：</b>有三个公共字符串：
"happy" 索引和 = (0 + 1) = 1.
"sad" 索引和 = (1 + 0) = 1.
"good" 索引和 = (2 + 2) = 4.
最小索引和的字符串是 "sad" 和 "happy"。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code>&nbsp;</li>
	<li><code>list1[i]</code> 和 <code>list2[i]</code> 由空格<meta charset="UTF-8" />&nbsp;<code>' '</code>&nbsp;和英文字母组成。</li>
	<li><code>list1</code> 的所有字符串都是 <strong>唯一</strong> 的。</li>
	<li><code>list2</code> 中的所有字符串都是 <strong>唯一</strong> 的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{d}$ 记录 $\textit{list2}$ 中的字符串和它们的下标，用一个变量 $\textit{mi}$ 记录最小的下标和。

然后遍历 $\textit{list1}$，对于每个字符串 $\textit{s}$，如果 $\textit{s}$ 在 $\textit{list2}$ 中出现，那么我们计算 $\textit{s}$ 在 $\textit{list1}$ 中的下标 $\textit{i}$ 和在 $\textit{list2}$ 中的下标 $\textit{j}$，如果 $\textit{i} + \textit{j} < \textit{mi}$，我们就更新答案数组 $\textit{ans}$ 为 $\textit{s}$，并且更新 $\textit{mi}$ 为 $\textit{i} + \textit{j}$；如果 $\textit{i} + \textit{j} = \textit{mi}$，我们就将 $\textit{s}$ 加入答案数组 $\textit{ans}$。

遍历结束后，返回答案数组 $\textit{ans}$ 即可。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRestaurant(self, list1: List[str], list2: List[str]) -> List[str]:
        d = {s: i for i, s in enumerate(list2)}
        ans = []
        mi = inf
        for i, s in enumerate(list1):
            if s in d:
                j = d[s]
                if i + j < mi:
                    mi = i + j
                    ans = [s]
                elif i + j == mi:
                    ans.append(s)
        return ans
```

#### Java

```java
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> d = new HashMap<>();
        for (int i = 0; i < list2.length; ++i) {
            d.put(list2[i], i);
        }
        List<String> ans = new ArrayList<>();
        int mi = 1 << 30;
        for (int i = 0; i < list1.length; ++i) {
            if (d.containsKey(list1[i])) {
                int j = d.get(list1[i]);
                if (i + j < mi) {
                    mi = i + j;
                    ans.clear();
                    ans.add(list1[i]);
                } else if (i + j == mi) {
                    ans.add(list1[i]);
                }
            }
        }
        return ans.toArray(new String[0]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
        unordered_map<string, int> d;
        for (int i = 0; i < list2.size(); ++i) {
            d[list2[i]] = i;
        }
        vector<string> ans;
        int mi = INT_MAX;
        for (int i = 0; i < list1.size(); ++i) {
            if (d.contains(list1[i])) {
                int j = d[list1[i]];
                if (i + j < mi) {
                    mi = i + j;
                    ans.clear();
                    ans.push_back(list1[i]);
                } else if (i + j == mi) {
                    ans.push_back(list1[i]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findRestaurant(list1 []string, list2 []string) []string {
	d := map[string]int{}
	for i, s := range list2 {
		d[s] = i
	}
	ans := []string{}
	mi := 1 << 30
	for i, s := range list1 {
		if j, ok := d[s]; ok {
			if i+j < mi {
				mi = i + j
				ans = []string{s}
			} else if i+j == mi {
				ans = append(ans, s)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findRestaurant(list1: string[], list2: string[]): string[] {
    const d = new Map<string, number>(list2.map((s, i) => [s, i]));
    let mi = Infinity;
    const ans: string[] = [];
    list1.forEach((s, i) => {
        if (d.has(s)) {
            const j = d.get(s)!;
            if (i + j < mi) {
                mi = i + j;
                ans.length = 0;
                ans.push(s);
            } else if (i + j === mi) {
                ans.push(s);
            }
        }
    });
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_restaurant(list1: Vec<String>, list2: Vec<String>) -> Vec<String> {
        let mut d = HashMap::new();
        for (i, s) in list2.iter().enumerate() {
            d.insert(s, i);
        }

        let mut ans = Vec::new();
        let mut mi = std::i32::MAX;

        for (i, s) in list1.iter().enumerate() {
            if let Some(&j) = d.get(s) {
                if (i as i32 + j as i32) < mi {
                    mi = i as i32 + j as i32;
                    ans = vec![s.clone()];
                } else if (i as i32 + j as i32) == mi {
                    ans.push(s.clone());
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
