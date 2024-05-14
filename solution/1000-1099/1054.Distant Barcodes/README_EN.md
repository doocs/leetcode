---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1054.Distant%20Barcodes/README_EN.md
rating: 1701
tags:
    - Greedy
    - Array
    - Hash Table
    - Counting
    - Sorting
    - Heap (Priority Queue)
---

# [1054. Distant Barcodes](https://leetcode.com/problems/distant-barcodes)

[中文文档](/solution/1000-1099/1054.Distant%20Barcodes/README.md)

## Description

<p>In a warehouse, there is a row of barcodes, where the <code>i<sup>th</sup></code> barcode is <code>barcodes[i]</code>.</p>

<p>Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> barcodes = [1,1,1,2,2,2]
<strong>Output:</strong> [2,1,2,1,2,1]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> barcodes = [1,1,1,1,2,2,3,3]
<strong>Output:</strong> [1,3,1,3,1,2,1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= barcodes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= barcodes[i] &lt;= 10000</code></li>
</ul>

## Solutions

### Solution 1: Counting + Sorting

First, we use a hash table or array $cnt$ to count the number of occurrences of each number in the array $barcodes$. Then, we sort the numbers in $barcodes$ according to their occurrence times in $cnt$ from large to small. If the occurrence times are the same, we sort them from small to large (to ensure the same numbers are adjacent).

Next, we create an answer array $ans$ of length $n$. We traverse the sorted $barcodes$, and sequentially fill the elements into the even index positions $0, 2, 4, \cdots$ of the answer array. Then, we fill the remaining elements into the odd index positions $1, 3, 5, \cdots$ of the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(M)$. Where $n$ and $M$ are the length of the array $barcodes$ and the maximum value in the array $barcodes$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def rearrangeBarcodes(self, barcodes: List[int]) -> List[int]:
        cnt = Counter(barcodes)
        barcodes.sort(key=lambda x: (-cnt[x], x))
        n = len(barcodes)
        ans = [0] * len(barcodes)
        ans[::2] = barcodes[: (n + 1) // 2]
        ans[1::2] = barcodes[(n + 1) // 2 :]
        return ans
```

```java
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        Integer[] t = new Integer[n];
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            t[i] = barcodes[i];
            mx = Math.max(mx, barcodes[i]);
        }
        int[] cnt = new int[mx + 1];
        for (int x : barcodes) {
            ++cnt[x];
        }
        Arrays.sort(t, (a, b) -> cnt[a] == cnt[b] ? a - b : cnt[b] - cnt[a]);
        int[] ans = new int[n];
        for (int k = 0, j = 0; k < 2; ++k) {
            for (int i = k; i < n; i += 2) {
                ans[i] = t[j++];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> rearrangeBarcodes(vector<int>& barcodes) {
        int mx = *max_element(barcodes.begin(), barcodes.end());
        int cnt[mx + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int x : barcodes) {
            ++cnt[x];
        }
        sort(barcodes.begin(), barcodes.end(), [&](int a, int b) {
            return cnt[a] > cnt[b] || (cnt[a] == cnt[b] && a < b);
        });
        int n = barcodes.size();
        vector<int> ans(n);
        for (int k = 0, j = 0; k < 2; ++k) {
            for (int i = k; i < n; i += 2) {
                ans[i] = barcodes[j++];
            }
        }
        return ans;
    }
};
```

```go
func rearrangeBarcodes(barcodes []int) []int {
	mx := slices.Max(barcodes)
	cnt := make([]int, mx+1)
	for _, x := range barcodes {
		cnt[x]++
	}
	sort.Slice(barcodes, func(i, j int) bool {
		a, b := barcodes[i], barcodes[j]
		if cnt[a] == cnt[b] {
			return a < b
		}
		return cnt[a] > cnt[b]
	})
	n := len(barcodes)
	ans := make([]int, n)
	for k, j := 0, 0; k < 2; k++ {
		for i := k; i < n; i, j = i+2, j+1 {
			ans[i] = barcodes[j]
		}
	}
	return ans
}
```

```ts
function rearrangeBarcodes(barcodes: number[]): number[] {
    const mx = Math.max(...barcodes);
    const cnt = Array(mx + 1).fill(0);
    for (const x of barcodes) {
        ++cnt[x];
    }
    barcodes.sort((a, b) => (cnt[a] === cnt[b] ? a - b : cnt[b] - cnt[a]));
    const n = barcodes.length;
    const ans = Array(n);
    for (let k = 0, j = 0; k < 2; ++k) {
        for (let i = k; i < n; i += 2, ++j) {
            ans[i] = barcodes[j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
