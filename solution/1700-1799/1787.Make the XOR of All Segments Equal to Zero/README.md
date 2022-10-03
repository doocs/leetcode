# [1787. 使所有区间的异或结果为零](https://leetcode.cn/problems/make-the-xor-of-all-segments-equal-to-zero)

[English Version](/solution/1700-1799/1787.Make%20the%20XOR%20of%20All%20Segments%20Equal%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>​​​ 和一个整数 <code>k</code>​​​​​ 。区间 <code>[left, right]</code>（<code>left <= right</code>）的 <strong>异或结果</strong> 是对下标位于 <code>left</code> 和 <code>right</code>（包括 <code>left</code> 和 <code>right</code> ）之间所有元素进行 <code>XOR</code> 运算的结果：<code>nums[left] XOR nums[left+1] XOR ... XOR nums[right]</code> 。</p>

<p>返回数组中 <strong>要更改的最小元素数</strong> ，以使所有长度为 <code>k</code> 的区间异或结果等于零。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,0,3,0], k = 1
<strong>输出：</strong>3
<strong>解释：</strong>将数组 [<strong>1</strong>,<strong>2</strong>,0,<strong>3</strong>,0] 修改为 [<strong>0</strong>,<strong>0</strong>,0,<strong>0</strong>,0]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,2,1,7,3,4,7], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>将数组 [3,4,<strong>5</strong>,<strong>2</strong>,<strong>1</strong>,7,3,4,7] 修改为 [3,4,<strong>7</strong>,<strong>3</strong>,<strong>4</strong>,7,3,4,7]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,1,2,5,1,2,6], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>将数组[1,2,<strong>4,</strong>1,2,<strong>5</strong>,1,2,<strong>6</strong>] 修改为 [1,2,<strong>3</strong>,1,2,<strong>3</strong>,1,2,<strong>3</strong>]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= nums.length <= 2000</code></li>
	<li><code>​​​​​​0 <= nums[i] < 2<sup>10</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

注意到数组 `nums` 在修改之后，任意长度为 $k$ 的区间异或结果都等于 $0$，那么对于任意的 $i$，都有：

$$
nums[i] \oplus nums[i+1] \oplus ... \oplus nums[i+k-1] = 0
$$

以及

$$
nums[i+1] \oplus nums[i+2] \oplus ... \oplus nums[i+k] = 0
$$

结合上面两个等式以及异或运算的性质，可以得到 $nums[i] \oplus nums[i+k] = 0$，即 $nums[i]=nums[i+k]$，我们发现，修改后的数组 `nums` 中的元素是以周期为 $k$ 的循环，对模 $k$ 同余的一组数必然只能取固定值，同时需要满足前 $k$ 个数异或结果为 $0$。

我们先对每一组 $i$ 进行计数，每一组的元素个数为 $size[i]$，每一组值为 $v$ 的元素个数为 $cnt[i][v]$。

接下来，我们可以用动态规划来求解。设 $f[i][j]$ 表示前 $i+1$ 组异或和为 $j$ 的最小修改次数。由于每一组的值只与前一组的值有关，因此我们可以用滚动数组优化空间复杂度。

重新定义 $f[j]$ 表示处理到当前组，且异或和为 $j$ 的最小修改次数。

状态转移时，有两种选择：一是将当前组的数全部都修改为同一个值，那么我们可以选择上一个代价最小的那个，加上这一组的元素个数 $size[i]$，此时的代价为 $\min{f[0..n]} + size[i]$；二是将当前组的数全部修改为当前组的某个值 $j$，枚举 $j$ 以及当前组的元素 $v$，那么前面的代价为 $f[j \oplus v]$，此时的代价为 $f[j \oplus v] + size[i] - cnt[i][v]$。取最小值即可。

最终答案为 $f[0]$。

时间复杂度 $O(2^{C}\times k + n)$。其中 $n$ 是数组 `nums` 的长度，而 $C$ 为 `nums` 中元素二进制表示的最大位数，本题中 $C=10$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minChanges(self, nums: List[int], k: int) -> int:
        n = 1 << 10
        cnt = [Counter() for _ in range(k)]
        size = [0] * k
        for i, v in enumerate(nums):
            cnt[i % k][v] += 1
            size[i % k] += 1
        f = [inf] * n
        f[0] = 0
        for i in range(k):
            g = [min(f) + size[i]] * n
            for j in range(n):
                for v, c in cnt[i].items():
                    g[j] = min(g[j], f[j ^ v] + size[i] - c)
            f = g
        return f[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minChanges(int[] nums, int k) {
        int n = 1 << 10;
        Map<Integer, Integer>[] cnt = new Map[k];
        int[] size = new int[k];
        for (int i = 0; i < k; ++i) {
            cnt[i] = new HashMap<>();
        }
        for (int i = 0; i < nums.length; ++i) {
            cnt[i % k].put(nums[i], cnt[i % k].getOrDefault(nums[i], 0) + 1);
            size[i % k]++;
        }
        int[] f = new int[n];
        Arrays.fill(f, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            int[] g = new int[n];
            Arrays.fill(g, min(f) + size[i]);
            for (int j = 0; j < n; ++j) {
                for (var e : cnt[i].entrySet()) {
                    int v = e.getKey(), c = e.getValue();
                    g[j] = Math.min(g[j], f[j ^ v] + size[i] - c);
                }
            }
            f = g;
        }
        return f[0];
    }

    private int min(int[] arr) {
        int mi = arr[0];
        for (int v : arr) {
            mi = Math.min(mi, v);
        }
        return mi;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minChanges(vector<int>& nums, int k) {
        int n = 1 << 10;
        vector<unordered_map<int, int>> cnt(k);
        vector<int> size(k);
        for (int i = 0; i < nums.size(); ++i) {
            cnt[i % k][nums[i]]++;
            size[i % k]++;
        }
        vector<int> f(n, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            int mi = *min_element(f.begin(), f.end());
            vector<int> g(n, mi + size[i]);
            for (int j = 0; j < n; ++j) {
                for (auto& [v, c] : cnt[i]) {
                    g[j] = min(g[j], f[j ^ v] + size[i] - c);
                }
            }
            f = move(g);
        }
        return f[0];
    }
};
```

### **Go**

```go
func minChanges(nums []int, k int) int {
	n := 1 << 10
	cnt := make([]map[int]int, k)
	for i := range cnt {
		cnt[i] = map[int]int{}
	}
	size := make([]int, k)
	for i, v := range nums {
		cnt[i%k][v]++
		size[i%k]++
	}
	f := make([]int, n)
	for i := 1; i < n; i++ {
		f[i] = 0x3f3f3f3f
	}
	for i, sz := range size {
		g := make([]int, n)
		x := min(f...) + sz
		for i := range g {
			g[i] = x
		}
		for j := 0; j < n; j++ {
			for v, c := range cnt[i] {
				g[j] = min(g[j], f[j^v]+sz-c)
			}
		}
		f = g
	}
	return f[0]
}

func min(a ...int) int {
	mi := a[0]
	for _, v := range a {
		if mi > v {
			mi = v
		}
	}
	return mi
}
```

### **...**

```

```

<!-- tabs:end -->
