# [274. H-Index](https://leetcode.com/problems/h-index)

[中文文档](/solution/0200-0299/0274.H-Index/README.md)

## Description

<p>Given an array of integers <code>citations</code> where <code>citations[i]</code> is the number of citations a researcher received for their <code>i<sup>th</sup></code> paper, return compute the researcher&#39;s <code>h</code><strong>-index</strong>.</p>

<p>According to the <a href="https://en.wikipedia.org/wiki/H-index" target="_blank">definition of h-index on Wikipedia</a>: A scientist has an index <code>h</code> if <code>h</code> of their <code>n</code> papers have at least <code>h</code> citations each, and the other <code>n &minus; h</code> papers have no more than <code>h</code> citations each.</p>

<p>If there are several possible values for <code>h</code>, the maximum one is taken as the <code>h</code><strong>-index</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> citations = [3,0,6,1,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> citations = [1,3,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == citations.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= citations[i] &lt;= 1000</code></li>
</ul>

## Solutions

The simplest solution is to judge after sort, but because `H` cannot be greater than the total number of papers, it can be optimized by counting sort.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        cnt = [0] * (n + 1)
        for c in citations:
            if c <= n:
                cnt[c] += 1
            else:
                cnt[n] += 1
        sum = 0
        for i in range(n, -1, -1):
            sum += cnt[i]
            if sum >= i:
                return i
        return 0
```

### **Java**

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int c : citations) {
            if (c <= n) {
                ++cnt[c];
            } else {
                ++cnt[n];
            }
        }
        int sum = 0;
        for (int i = n; i >= 0; --i) {
            sum += cnt[i];
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }
}
```

### **TypeScript**

```ts
function hIndex(citations: number[]): number {
    let n = citations.length;
    let cnt = new Array(n + 1).fill(0);
    for (let c of citations) {
        if (c <= n) {
            ++cnt[c];
        } else {
            ++cnt[n];
        }
    }
    let sum = 0;
    for (let i = n; i > -1; --i) {
        sum += cnt[i];
        if (sum >= i) {
            return i;
        }
    }
    return 0;
}
```

### **Go**

Use binary search to locate the maximum value that meets the conditions

```go
func hIndex(citations []int) int {
	n := len(citations)
	left, right := 0, n
	for left+1 < right {
		mid := int(uint(left+right) >> 1)
		if check(citations, mid) {
			left = mid
		} else {
			right = mid
		}
	}
	if check(citations, right) {
		return right
	}
	return left
}

func check(citations []int, mid int) bool {
	cnt := 0
	for _, citation := range citations {
		if citation >= mid {
			cnt++
		}
	}
	return cnt >= mid
}
```

### **...**

```

```

<!-- tabs:end -->
