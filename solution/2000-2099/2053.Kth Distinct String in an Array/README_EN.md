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

### Solution 1: Counting

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthDistinct(self, arr: List[str], k: int) -> str:
        counter = Counter(arr)
        for v in arr:
            if counter[v] == 1:
                k -= 1
                if k == 0:
                    return v
        return ''
```

#### Java

```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for (String v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        for (String v : arr) {
            if (counter.get(v) == 1) {
                --k;
                if (k == 0) {
                    return v;
                }
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
        unordered_map<string, int> counter;
        for (auto& v : arr) ++counter[v];
        for (auto& v : arr) {
            if (counter[v] == 1) {
                --k;
                if (k == 0) return v;
            }
        }
        return "";
    }
};
```

#### Go

```go
func kthDistinct(arr []string, k int) string {
	counter := make(map[string]int)
	for _, v := range arr {
		counter[v]++
	}
	for _, v := range arr {
		if counter[v] == 1 {
			k--
			if k == 0 {
				return v
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

    for (const x of arr) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    for (const [x, c] of cnt) {
        if (c === 1) k--;
        if (!k) return x;
    }

    return '';
}
```

#### JavaScript

```js
function kthDistinct(arr k) {
    const cnt = new Map();

    for (const x of arr) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    for (const [x, c] of cnt) {
        if (c === 1) k--;
        if (!k) return x;
    }

    return '';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Hash Set

<!-- tabs:start -->

#### TypeScript

```ts
function kthDistinct(arr: string[], k: number): string {
    const distinct = new Set<string>();
    const duplicate = new Set<string>();

    for (const x of arr) {
        if (distinct.has(x)) {
            distinct.delete(x);
            duplicate.add(x);
        } else if (!duplicate.has(x)) distinct.add(x);
    }

    for (const x of distinct) {
        if (--k === 0) return x;
    }

    return '';
}
```

#### JavaScript

```js
function kthDistinct(arr, k) {
    const distinct = new Set();
    const duplicate = new Set();

    for (const x of arr) {
        if (distinct.has(x)) {
            distinct.delete(x);
            duplicate.add(x);
        } else if (!duplicate.has(x)) distinct.add(x);
    }

    for (const x of distinct) {
        if (--k === 0) return x;
    }

    return '';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
