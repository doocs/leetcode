# [2551. 将珠子放入背包中](https://leetcode.cn/problems/put-marbles-in-bags)

[English Version](/solution/2500-2599/2551.Put%20Marbles%20in%20Bags/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有&nbsp;<code>k</code>&nbsp;个背包。给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>weights</code>&nbsp;，其中&nbsp;<code>weights[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个珠子的重量。同时给你整数 <code>k</code>&nbsp;。</p>

<p>请你按照如下规则将所有的珠子放进&nbsp;<code>k</code>&nbsp;个背包。</p>

<ul>
	<li>没有背包是空的。</li>
	<li>如果第&nbsp;<code>i</code>&nbsp;个珠子和第&nbsp;<code>j</code>&nbsp;个珠子在同一个背包里，那么下标在&nbsp;<code>i</code>&nbsp;到&nbsp;<code>j</code>&nbsp;之间的所有珠子都必须在这同一个背包中。</li>
	<li>如果一个背包有下标从&nbsp;<code>i</code>&nbsp;到&nbsp;<code>j</code>&nbsp;的所有珠子，那么这个背包的价格是&nbsp;<code>weights[i] + weights[j]</code>&nbsp;。</li>
</ul>

<p>一个珠子分配方案的 <strong>分数</strong>&nbsp;是所有 <code>k</code>&nbsp;个背包的价格之和。</p>

<p>请你返回所有分配方案中，<strong>最大分数</strong>&nbsp;与 <strong>最小分数</strong>&nbsp;的 <strong>差值</strong>&nbsp;为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>weights = [1,3,5,1], k = 2
<b>输出：</b>4
<b>解释：</b>
分配方案 [1],[3,5,1] 得到最小得分 (1+1) + (3+1) = 6 。
分配方案 [1,3],[5,1] 得到最大得分 (1+3) + (5+1) = 10 。
所以差值为 10 - 6 = 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>weights = [1, 3], k = 2
<b>输出：</b>0
<b>解释：</b>唯一的分配方案为 [1],[3] 。
最大最小得分相等，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= weights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= weights[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：问题转化 + 排序**

我们可以将问题转化为：将数组 `weights` 分成 $k$ 个连续的子数组，也就是说，我们要找到 $k-1$ 个分割点，每个分割点的价格是分割点左右两个元素的和，求最大的 $k-1$ 个分割点的价格之和与最小的 $k-1$ 个分割点的价格之和的差值，即为答案。

因此，我们可以处理数组 `weights`，将其转化为一个长度为 $n-1$ 的数组 `arr`，其中 `arr[i] = weights[i] + weights[i+1]`，然后对数组 `arr` 进行排序，最后求出最大的 $k-1$ 个分割点的价格之和与最小的 $k-1$ 个分割点的价格之和的差值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `weights` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def putMarbles(self, weights: List[int], k: int) -> int:
        arr = sorted(a + b for a, b in pairwise(weights))
        return sum(arr[len(arr) - k + 1 :]) - sum(arr[: k - 1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] arr = new int[n - 1];
        for (int i = 0; i < n - 1; ++i) {
            arr[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < k - 1; ++i) {
            ans -= arr[i];
            ans += arr[n - 2 - i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long putMarbles(vector<int>& weights, int k) {
        int n = weights.size();
        vector<int> arr(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            arr[i] = weights[i] + weights[i + 1];
        }
        sort(arr.begin(), arr.end());
        long long ans = 0;
        for (int i = 0; i < k - 1; ++i) {
            ans -= arr[i];
            ans += arr[n - 2 - i];
        }
        return ans;
    }
};
```

### **Go**

```go
func putMarbles(weights []int, k int) (ans int64) {
	n := len(weights)
	arr := make([]int, n-1)
	for i, w := range weights[:n-1] {
		arr[i] = w + weights[i+1]
	}
	sort.Ints(arr)
	for i := 0; i < k-1; i++ {
		ans += int64(arr[n-2-i] - arr[i])
	}
	return
}
```

### **TypeScript**

```ts
function putMarbles(weights: number[], k: number): number {
    const n = weights.length;
    const arr: number[] = [];
    for (let i = 0; i < n - 1; ++i) {
        arr.push(weights[i] + weights[i + 1]);
    }
    arr.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < k - 1; ++i) {
        ans += arr[n - i - 2] - arr[i];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
