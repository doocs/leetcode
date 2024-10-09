---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2053.Kth%20Distinct%20String%20in%20an%20Array/README_EN.md
rating: 1350
source: Biweekly Contest 64 Q1
tags:
    - Array
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [2053. Kth Distinct String in an Array](https://leetcode.com/problems/kth-distinct-string-in-an-array)

[中文文档](/solution/2000-2099/2053.Kth%20Distinct%20String%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>A <strong>distinct string</strong> is a string that is present only <strong>once</strong> in an array.</p>

<p>Given an array of strings <code>arr</code>, and an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code><em> <strong>distinct string</strong> present in </em><code>arr</code>. If there are <strong>fewer</strong> than <code>k</code> distinct strings, return <em>an <strong>empty string </strong></em><code>&quot;&quot;</code>.</p>

<p>Note that the strings are considered in the <strong>order in which they appear</strong> in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;d&quot;,&quot;b&quot;,&quot;c&quot;,&quot;b&quot;,&quot;c&quot;,&quot;a&quot;], k = 2
<strong>Output:</strong> &quot;a&quot;
<strong>Explanation:</strong>
The only distinct strings in arr are &quot;d&quot; and &quot;a&quot;.
&quot;d&quot; appears 1<sup>st</sup>, so it is the 1<sup>st</sup> distinct string.
&quot;a&quot; appears 2<sup>nd</sup>, so it is the 2<sup>nd</sup> distinct string.
Since k == 2, &quot;a&quot; is returned. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;aaa&quot;,&quot;aa&quot;,&quot;a&quot;], k = 1
<strong>Output:</strong> &quot;aaa&quot;
<strong>Explanation:</strong>
All strings in arr are distinct, so the 1<sup>st</sup> string &quot;aaa&quot; is returned.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;a&quot;,&quot;b&quot;,&quot;a&quot;], k = 3
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong>
The only distinct string is &quot;b&quot;. Since there are fewer than 3 distinct strings, we return an empty string &quot;&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 5</code></li>
	<li><code>arr[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Counting

We can use a hash table $\textit{cnt}$ to record the number of occurrences of each string. Then, we traverse the array once more. For each string, if its occurrence count is $1$, we decrement $k$ by one. When $k$ reaches $0$, we return the current string.

Time complexity is $O(L)$, and space complexity is $O(L)$, where $L$ is the total length of all strings in the array $\textit{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthDistinct(self, arr: List[str], k: int) -> str:
        cnt = Counter(arr)
        for s in arr:
            if cnt[s] == 1:
                k -= 1
                if k == 0:
                    return s
        return ""
```

#### Java

```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : arr) {
            cnt.merge(s, 1, Integer::sum);
        }
        for (String s : arr) {
            if (cnt.get(s) == 1 && --k == 0) {
                return s;
            }
        }
        return "";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string kthDistinct(vector<string>& arr, int k) {
        unordered_map<string, int> cnt;
        for (const auto& s : arr) {
            ++cnt[s];
        }
        for (const auto& s : arr) {
            if (cnt[s] == 1 && --k == 0) {
                return s;
            }
        }
        return "";
    }
};
```

#### Go

```go
func kthDistinct(arr []string, k int) string {
	cnt := map[string]int{}
	for _, s := range arr {
		cnt[s]++
	}
	for _, s := range arr {
		if cnt[s] == 1 {
			k--
			if k == 0 {
				return s
			}
		}
	}
	return ""
}
```

#### TypeScript

```ts
function kthDistinct(arr: string[], k: number): string {
    const cnt = new Map<string, number>();
    for (const s of arr) {
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    for (const s of arr) {
        if (cnt.get(s) === 1 && --k === 0) {
            return s;
        }
    }
    return '';
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn kth_distinct(arr: Vec<String>, mut k: i32) -> String {
        let mut cnt = HashMap::new();

        for s in &arr {
            *cnt.entry(s).or_insert(0) += 1;
        }

        for s in &arr {
            if *cnt.get(s).unwrap() == 1 {
                k -= 1;
                if k == 0 {
                    return s.clone();
                }
            }
        }

        "".to_string()
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} arr
 * @param {number} k
 * @return {string}
 */
var kthDistinct = function (arr, k) {
    const cnt = new Map();
    for (const s of arr) {
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    for (const s of arr) {
        if (cnt.get(s) === 1 && --k === 0) {
            return s;
        }
    }
    return '';
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
