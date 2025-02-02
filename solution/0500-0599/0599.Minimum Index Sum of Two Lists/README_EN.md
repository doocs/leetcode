---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README_EN.md
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [599. Minimum Index Sum of Two Lists](https://leetcode.com/problems/minimum-index-sum-of-two-lists)

[中文文档](/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{d}$ to record the strings in $\textit{list2}$ and their indices, and a variable $\textit{mi}$ to record the minimum index sum.

Then, we traverse $\textit{list1}$. For each string $\textit{s}$, if $\textit{s}$ appears in $\textit{list2}$, we calculate the index $\textit{i}$ of $\textit{s}$ in $\textit{list1}$ and the index $\textit{j}$ in $\textit{list2}$. If $\textit{i} + \textit{j} < \textit{mi}$, we update the answer array $\textit{ans}$ to $\textit{s}$ and update $\textit{mi}$ to $\textit{i} + \textit{j}$. If $\textit{i} + \textit{j} = \textit{mi}$, we add $\textit{s}$ to the answer array $\textit{ans}$.

After traversing, return the answer array $\textit{ans}$.

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
