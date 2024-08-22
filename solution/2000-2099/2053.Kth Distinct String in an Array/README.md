---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2053.Kth%20Distinct%20String%20in%20an%20Array/README.md
rating: 1350
source: 第 64 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [2053. 数组中第 K 个独一无二的字符串](https://leetcode.cn/problems/kth-distinct-string-in-an-array)

[English Version](/solution/2000-2099/2053.Kth%20Distinct%20String%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>独一无二的字符串</strong>&nbsp;指的是在一个数组中只出现过 <strong>一次</strong>&nbsp;的字符串。</p>

<p>给你一个字符串数组&nbsp;<code>arr</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回&nbsp;<code>arr</code>&nbsp;中第&nbsp;<code>k</code>&nbsp;个&nbsp;<strong>独一无二的字符串</strong>&nbsp;。如果&nbsp;<strong>少于</strong>&nbsp;<code>k</code>&nbsp;个独一无二的字符串，那么返回&nbsp;<strong>空字符串</strong>&nbsp;<code>""</code>&nbsp;。</p>

<p>注意，按照字符串在原数组中的 <strong>顺序</strong>&nbsp;找到第 <code>k</code>&nbsp;个独一无二字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><b>输入：</b>arr = ["d","b","c","b","c","a"], k = 2
<b>输出：</b>"a"
<strong>解释：</strong>
arr 中独一无二字符串包括 "d" 和 "a"<code>&nbsp;。</code>
"d" 首先出现，所以它是第 1 个独一无二字符串。
"a" 第二个出现，所以它是 2 个独一无二字符串。
由于 k == 2 ，返回 "a" 。
</pre>

<p><strong>示例 2:</strong></p>

<pre><b>输入：</b>arr = ["aaa","aa","a"], k = 1
<b>输出：</b>"aaa"
<strong>解释：</strong>
arr 中所有字符串都是独一无二的，所以返回第 1 个字符串 "aaa" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>arr = ["a","b","a"], k = 3
<b>输出：</b>""
<strong>解释：</strong>
唯一一个独一无二字符串是 "b" 。由于少于 3 个独一无二字符串，我们返回空字符串 "" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 5</code></li>
	<li><code>arr[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 计数

我们可以用一个哈希表 $\textit{cnt}$ 记录每个字符串出现的次数，然后再遍历一次数组，对于每个字符串，如果它出现的次数为 $1$，那么就将 $k$ 减一，直到 $k$ 减为 $0$，返回当前字符串即可。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为数组 $\textit{arr}$ 所有字符串的长度之和。

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
