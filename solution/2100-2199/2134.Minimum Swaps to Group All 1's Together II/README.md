# [2134. 最少交换次数来组合所有的 1 II](https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii)

[English Version](/solution/2100-2199/2134.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>交换</strong> 定义为选中一个数组中的两个 <strong>互不相同</strong> 的位置并交换二者的值。</p>

<p><strong>环形</strong> 数组是一个数组，可以认为 <strong>第一个</strong> 元素和 <strong>最后一个</strong> 元素 <strong>相邻</strong> 。</p>

<p>给你一个 <strong>二进制环形</strong> 数组 <code>nums</code> ，返回在 <strong>任意位置</strong> 将数组中的所有 <code>1</code> 聚集在一起需要的最少交换次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,0,1,1,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>这里列出一些能够将所有 1 聚集在一起的方案：
[0,<strong><em>0</em></strong>,<em><strong>1</strong></em>,1,1,0,0] 交换 1 次。
[0,1,<em><strong>1</strong></em>,1,<em><strong>0</strong></em>,0,0] 交换 1 次。
[1,1,0,0,0,0,1] 交换 2 次（利用数组的环形特性）。
无法在交换 0 次的情况下将数组中的所有 1 聚集在一起。
因此，需要的最少交换次数为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,1,1,0,0,1,1,0]
<strong>输出：</strong>2
<strong>解释：</strong>这里列出一些能够将所有 1 聚集在一起的方案：
[1,1,1,0,0,0,0,1,1] 交换 2 次（利用数组的环形特性）。
[1,1,1,1,1,0,0,0,0] 交换 2 次。
无法在交换 0 次或 1 次的情况下将数组中的所有 1 聚集在一起。
因此，需要的最少交换次数为 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,0,0,1]
<strong>输出：</strong>0
<strong>解释：</strong>得益于数组的环形特性，所有的 1 已经聚集在一起。
因此，需要的最少交换次数为 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 为 <code>0</code> 或者 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 滑动窗口。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        cnt = nums.count(1)
        n = len(nums)
        s = [0] * ((n << 1) + 1)
        for i in range(n << 1):
            s[i + 1] = s[i] + nums[i % n]
        mx = 0
        for i in range(n << 1):
            j = i + cnt - 1
            if j < (n << 1):
                mx = max(mx, s[j + 1] - s[i])
        return cnt - mx
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSwaps(int[] nums) {
        int cnt = 0;
        for (int v : nums) {
            cnt += v;
        }
        int n = nums.length;
        int[] s = new int[(n << 1) + 1];
        for (int i = 0; i < (n << 1); ++i) {
            s[i + 1] = s[i] + nums[i % n];
        }
        int mx = 0;
        for (int i = 0; i < (n << 1); ++i) {
            int j = i + cnt - 1;
            if (j < (n << 1)) {
                mx = Math.max(mx, s[j + 1] - s[i]);
            }
        }
        return cnt - mx;
    }
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function minSwaps(nums: number[]): number {
    const n = nums.length;
    const m = nums.reduce((a, c) => a + c, 0);
    let cnt = nums.reduce((a, c, i) => a + (i < m ? c : 0), 0);
    let ans = cnt;
    for (let i = m; i < m + n; i++) {
        let prev = nums[i - m];
        let post = nums[i % n];
        cnt += post - prev;
        ans = Math.max(cnt, ans);
    }
    return m - ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int cnt = 0;
        for (int& v : nums) cnt += v;
        int n = nums.size();
        vector<int> s((n << 1) + 1);
        for (int i = 0; i < (n << 1); ++i) s[i + 1] = s[i] + nums[i % n];
        int mx = 0;
        for (int i = 0; i < (n << 1); ++i) {
            int j = i + cnt - 1;
            if (j < (n << 1)) mx = max(mx, s[j + 1] - s[i]);
        }
        return cnt - mx;
    }
};
```

### **Go**

```go
func minSwaps(nums []int) int {
	cnt := 0
	for _, v := range nums {
		cnt += v
	}
	n := len(nums)
	s := make([]int, (n<<1)+1)
	for i := 0; i < (n << 1); i++ {
		s[i+1] = s[i] + nums[i%n]
	}
	mx := 0
	for i := 0; i < (n << 1); i++ {
		j := i + cnt - 1
		if j < (n << 1) {
			mx = max(mx, s[j+1]-s[i])
		}
	}
	return cnt - mx
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
