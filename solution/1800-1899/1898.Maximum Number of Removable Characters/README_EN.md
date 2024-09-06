---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1898.Maximum%20Number%20of%20Removable%20Characters/README_EN.md
rating: 1912
source: Weekly Contest 245 Q2
tags:
    - Array
    - Two Pointers
    - String
    - Binary Search
---

<!-- problem:start -->

# [1898. Maximum Number of Removable Characters](https://leetcode.com/problems/maximum-number-of-removable-characters)

[中文文档](/solution/1800-1899/1898.Maximum%20Number%20of%20Removable%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>p</code> where <code>p</code> is a <strong>subsequence </strong>of <code>s</code>. You are also given a <strong>distinct 0-indexed </strong>integer array <code>removable</code> containing a subset of indices of <code>s</code> (<code>s</code> is also <strong>0-indexed</strong>).</p>

<p>You want to choose an integer <code>k</code> (<code>0 &lt;= k &lt;= removable.length</code>) such that, after removing <code>k</code> characters from <code>s</code> using the <strong>first</strong> <code>k</code> indices in <code>removable</code>, <code>p</code> is still a <strong>subsequence</strong> of <code>s</code>. More formally, you will mark the character at <code>s[removable[i]]</code> for each <code>0 &lt;= i &lt; k</code>, then remove all marked characters and check if <code>p</code> is still a subsequence.</p>

<p>Return <em>the <strong>maximum</strong> </em><code>k</code><em> you can choose such that </em><code>p</code><em> is still a <strong>subsequence</strong> of </em><code>s</code><em> after the removals</em>.</p>

<p>A <strong>subsequence</strong> of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcacb&quot;, p = &quot;ab&quot;, removable = [3,1,0]
<strong>Output:</strong> 2
<strong>Explanation</strong>: After removing the characters at indices 3 and 1, &quot;a<s><strong>b</strong></s>c<s><strong>a</strong></s>cb&quot; becomes &quot;accb&quot;.
&quot;ab&quot; is a subsequence of &quot;<strong><u>a</u></strong>cc<strong><u>b</u></strong>&quot;.
If we remove the characters at indices 3, 1, and 0, &quot;<s><strong>ab</strong></s>c<s><strong>a</strong></s>cb&quot; becomes &quot;ccb&quot;, and &quot;ab&quot; is no longer a subsequence.
Hence, the maximum k is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcbddddd&quot;, p = &quot;abcd&quot;, removable = [3,2,1,4,5,6]
<strong>Output:</strong> 1
<strong>Explanation</strong>: After removing the character at index 3, &quot;abc<s><strong>b</strong></s>ddddd&quot; becomes &quot;abcddddd&quot;.
&quot;abcd&quot; is a subsequence of &quot;<u><strong>abcd</strong></u>dddd&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcab&quot;, p = &quot;abc&quot;, removable = [0,1,2,3,4]
<strong>Output:</strong> 0
<strong>Explanation</strong>: If you remove the first index in the array removable, &quot;abc&quot; is no longer a subsequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= p.length &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= removable.length &lt; s.length</code></li>
	<li><code>0 &lt;= removable[i] &lt; s.length</code></li>
	<li><code>p</code> is a <strong>subsequence</strong> of <code>s</code>.</li>
	<li><code>s</code> and <code>p</code> both consist of lowercase English letters.</li>
	<li>The elements in <code>removable</code> are <strong>distinct</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        def check(k):
            i = j = 0
            ids = set(removable[:k])
            while i < m and j < n:
                if i not in ids and s[i] == p[j]:
                    j += 1
                i += 1
            return j == n

        m, n = len(s), len(p)
        left, right = 0, len(removable)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

#### Java

```java
class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        int left = 0, right = removable.length;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(s, p, removable, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(String s, String p, int[] removable, int mid) {
        int m = s.length(), n = p.length(), i = 0, j = 0;
        Set<Integer> ids = new HashSet<>();
        for (int k = 0; k < mid; ++k) {
            ids.add(removable[k]);
        }
        while (i < m && j < n) {
            if (!ids.contains(i) && s.charAt(i) == p.charAt(j)) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumRemovals(string s, string p, vector<int>& removable) {
        int left = 0, right = removable.size();
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(s, p, removable, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    bool check(string s, string p, vector<int>& removable, int mid) {
        int m = s.size(), n = p.size(), i = 0, j = 0;
        unordered_set<int> ids;
        for (int k = 0; k < mid; ++k) {
            ids.insert(removable[k]);
        }
        while (i < m && j < n) {
            if (ids.count(i) == 0 && s[i] == p[j]) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
};
```

#### Go

```go
func maximumRemovals(s string, p string, removable []int) int {
	check := func(k int) bool {
		ids := make(map[int]bool)
		for _, r := range removable[:k] {
			ids[r] = true
		}
		var i, j int
		for i < len(s) && j < len(p) {
			if !ids[i] && s[i] == p[j] {
				j++
			}
			i++
		}
		return j == len(p)
	}

	left, right := 0, len(removable)
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function maximumRemovals(s: string, p: string, removable: number[]): number {
    let left = 0,
        right = removable.length;
    while (left < right) {
        let mid = (left + right + 1) >> 1;
        if (isSub(s, p, new Set(removable.slice(0, mid)))) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}

function isSub(str: string, sub: string, idxes: Set<number>): boolean {
    let m = str.length,
        n = sub.length;
    let i = 0,
        j = 0;
    while (i < m && j < n) {
        if (!idxes.has(i) && str.charAt(i) == sub.charAt(j)) {
            ++j;
        }
        ++i;
    }
    return j == n;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn maximum_removals(s: String, p: String, removable: Vec<i32>) -> i32 {
        let m = s.len();
        let n = p.len();
        let s = s.as_bytes();
        let p = p.as_bytes();

        let check = |k| {
            let mut i = 0;
            let mut j = 0;
            let ids: HashSet<i32> = removable[..k].iter().cloned().collect();
            while i < m && j < n {
                if !ids.contains(&(i as i32)) && s[i] == p[j] {
                    j += 1;
                }
                i += 1;
            }
            j == n
        };

        let mut left = 0;
        let mut right = removable.len();
        while left + 1 < right {
            let mid = left + (right - left) / 2;
            if check(mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if check(right) {
            return right as i32;
        }
        left as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} p
 * @param {number[]} removable
 * @return {number}
 */
function maximumRemovals(s, p, removable) {
    const str_len = s.length;
    const sub_len = p.length;

    /**
     * @param {number} k
     * @return {boolean}
     */
    function isSub(k) {
        const removed = new Set(removable.slice(0, k));

        let sub_i = 0;
        for (let str_i = 0; str_i < str_len; ++str_i) {
            if (s.charAt(str_i) === p.charAt(sub_i) && !removed.has(str_i)) {
                ++sub_i;
                if (sub_i >= sub_len) {
                    break;
                }
            }
        }
        return sub_i === sub_len;
    }

    let left = 0;
    let right = removable.length;

    while (left < right) {
        const middle = (left + right) >> 1;
        if (isSub(middle + 1)) {
            left = middle + 1;
        } else {
            right = middle;
        }
    }
    return left;
}
```

#### Kotlin

```kotlin
class Solution {
  fun maximumRemovals(s: String, p: String, removable: IntArray): Int {
      val strLen = s.length
      val subLen = p.length

      fun isSub(k: Int): Boolean {
          val removed = removable.sliceArray(0 ..< k).toHashSet()

          var subIndex = 0
          for (strIndex in 0 ..< strLen) {
              if (s[strIndex] == p[subIndex] && !removed.contains(strIndex)) {
                  ++subIndex
                  if (subIndex >= subLen) {
                      break
                  }
              }
          }

          return subIndex == subLen
      }

      var left = 0
      var right = removable.size

      while (left < right) {
          val middle = (left + right) / 2
          if (isSub(middle + 1)) {
              left = middle + 1
          } else {
              right = middle
          }
      }
      return left
  }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
