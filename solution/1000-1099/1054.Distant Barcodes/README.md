# [1054. 距离相等的条形码](https://leetcode.cn/problems/distant-barcodes)

[English Version](/solution/1000-1099/1054.Distant%20Barcodes/README_EN.md)

<!-- tags:贪心,数组,哈希表,计数,排序,堆（优先队列） -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个仓库里，有一排条形码，其中第 <code>i</code> 个条形码为&nbsp;<code>barcodes[i]</code>。</p>

<p>请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>barcodes = [1,1,1,2,2,2]
<strong>输出：</strong>[2,1,2,1,2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>barcodes = [1,1,1,1,2,2,3,3]
<strong>输出：</strong>[1,3,1,3,2,1,2,1]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= barcodes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= barcodes[i] &lt;= 10000</code></li>
</ul>

## 解法

### 方法一：计数 + 排序

我们先用哈希表或数组 $cnt$ 统计数组 $barcodes$ 中各个数出现的次数，然后将 $barcodes$ 中的数按照它们在 $cnt$ 中出现的次数从大到小排序，如果出现次数相同，那么就按照数的大小从小到大排序（确保相同的数相邻）。

接下来，我们创建一个长度为 $n$ 的答案数组 $ans$，然后遍历排好序的 $barcodes$，将元素依次填入答案数组的 $0, 2, 4, \cdots$ 等偶数下标位置，然后将剩余元素依次填入答案数组的 $1, 3, 5, \cdots$ 等奇数下标位置即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(M)$。其中 $n$ 和 $M$ 分别是数组 $barcodes$ 的长度以及数组 $barcodes$ 中的最大值。

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
