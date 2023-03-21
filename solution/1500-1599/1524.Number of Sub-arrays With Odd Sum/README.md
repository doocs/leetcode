# [1524. 和为奇数的子数组数目](https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum)

[English Version](/solution/1500-1599/1524.Number%20of%20Sub-arrays%20With%20Odd%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;。请你返回和为 <strong>奇数</strong>&nbsp;的子数组数目。</p>

<p>由于答案可能会很大，请你将结果对&nbsp;<code>10^9 + 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,3,5]
<strong>输出：</strong>4
<strong>解释：</strong>所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
所有子数组的和为 [1,4,9,3,8,5].
奇数和包括 [1,9,3,5] ，所以答案为 4 。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>arr = [2,4,6]
<strong>输出：</strong>0
<strong>解释：</strong>所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
所有子数组和为 [2,6,12,4,10,6] 。
所有子数组和都是偶数，所以答案为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4,5,6,7]
<strong>输出：</strong>16
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [100,100,99,99]
<strong>输出：</strong>4
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [7]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 计数器**

我们定义一个长度为 $2$ 的数组 $cnt$ 作为计数器，其中 $cnt[0]$ 和 $cnt[1]$ 分别表示前缀和为偶数和奇数的子数组的个数。初始时 $cnt[0] = 1$，而 $cnt[1] = 0$。

接下来，我们维护当前的前缀和 $s$，初始时 $s = 0$。

遍历数组 $arr$，对于遍历到的每个元素 $x$，我们将 $s$ 的值加上 $x$，然后根据 $s$ 的奇偶性，将 $cnt[s \mod 2 \oplus 1]$ 的值累加到答案中，然后我们将 $cnt[s \mod 2]$ 的值加 $1$。

遍历结束后，我们即可得到答案。注意答案的取模运算。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numOfSubarrays(self, arr: List[int]) -> int:
        mod = 10**9 + 7
        cnt = [1, 0]
        ans = s = 0
        for x in arr:
            s += x
            ans = (ans + cnt[s & 1 ^ 1]) % mod
            cnt[s & 1] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numOfSubarrays(int[] arr) {
        final int mod = (int) 1e9 + 7;
        int[] cnt = {1, 0};
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % mod;
            ++cnt[s & 1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        const int mod = 1e9 + 7;
        int cnt[2] = {1, 0};
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % mod;
            ++cnt[s & 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func numOfSubarrays(arr []int) (ans int) {
	const mod int = 1e9 + 7
	cnt := [2]int{1, 0}
	s := 0
	for _, x := range arr {
		s += x
		ans = (ans + cnt[s&1^1]) % mod
		cnt[s&1]++
	}
	return
}
```

### **TypeScript**

```ts
function numOfSubarrays(arr: number[]): number {
    let ans = 0;
    let s = 0;
    const cnt: number[] = [1, 0];
    const mod = 1e9 + 7;
    for (const x of arr) {
        s += x;
        ans = (ans + cnt[(s & 1) ^ 1]) % mod;
        cnt[s & 1]++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
