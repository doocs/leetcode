# [2584. 分割数组使乘积互质](https://leetcode.cn/problems/split-the-array-to-make-coprime-products)

[English Version](/solution/2500-2599/2584.Split%20the%20Array%20to%20Make%20Coprime%20Products/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，下标从 <strong>0</strong> 开始。</p>

<p>如果在下标 <code>i</code> 处 <strong>分割</strong> 数组，其中 <code>0 &lt;= i &lt;= n - 2</code> ，使前 <code>i + 1</code> 个元素的乘积和剩余元素的乘积互质，则认为该分割 <strong>有效</strong> 。</p>

<ul>
	<li>例如，如果 <code>nums = [2, 3, 3]</code> ，那么在下标 <code>i = 0</code> 处的分割有效，因为 <code>2</code> 和 <code>9</code> 互质，而在下标 <code>i = 1</code> 处的分割无效，因为 <code>6</code> 和 <code>3</code> 不互质。在下标 <code>i = 2</code> 处的分割也无效，因为 <code>i == n - 1</code> 。</li>
</ul>

<p>返回可以有效分割数组的最小下标 <code>i</code> ，如果不存在有效分割，则返回 <code>-1</code> 。</p>

<p>当且仅当 <code>gcd(val1, val2) == 1</code> 成立时，<code>val1</code> 和 <code>val2</code> 这两个值才是互质的，其中 <code>gcd(val1, val2)</code> 表示 <code>val1</code> 和 <code>val2</code> 的最大公约数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2584.Split%20the%20Array%20to%20Make%20Coprime%20Products/images/second.png" style="width: 450px; height: 211px;" /></p>

<pre>
<strong>输入：</strong>nums = [4,7,8,15,3,5]
<strong>输出：</strong>2
<strong>解释：</strong>上表展示了每个下标 i 处的前 i + 1 个元素的乘积、剩余元素的乘积和它们的最大公约数的值。
唯一一个有效分割位于下标 2 。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2584.Split%20the%20Array%20to%20Make%20Coprime%20Products/images/capture.png" style="width: 450px; height: 215px;" /></p>

<pre>
<strong>输入：</strong>nums = [4,7,15,8,3,5]
<strong>输出：</strong>-1
<strong>解释：</strong>上表展示了每个下标 i 处的前 i + 1 个元素的乘积、剩余元素的乘积和它们的最大公约数的值。
不存在有效分割。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：质因数分解**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findValidSplit(self, nums: List[int]) -> int:
        first = {}
        n = len(nums)
        last = list(range(n))
        for i, x in enumerate(nums):
            j = 2
            while j <= x // j:
                if x % j == 0:
                    if j in first:
                        last[first[j]] = i
                    else:
                        first[j] = i
                    while x % j == 0:
                        x //= j
                j += 1
            if x > 1:
                if x in first:
                    last[first[x]] = i
                else:
                    first[x] = i
        mx = last[0]
        for i, x in enumerate(last):
            if mx < i:
                return mx
            mx = max(mx, x)
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findValidSplit(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        int n = nums.length;
        int[] last = new int[n];
        for (int i = 0; i < n; ++i) {
            last[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = 2; j <= x / j; ++j) {
                if (x % j == 0) {
                    if (first.containsKey(j)) {
                        last[first.get(j)] = i;
                    } else {
                        first.put(j, i);
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (first.containsKey(x)) {
                    last[first.get(x)] = i;
                } else {
                    first.put(x, i);
                }
            }
        }
        int mx = last[0];
        for (int i = 0; i < n; ++i) {
            if (mx < i) {
                return mx;
            }
            mx = Math.max(mx, last[i]);
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findValidSplit(vector<int>& nums) {
        unordered_map<int, int> first;
        int n = nums.size();
        vector<int> last(n);
        iota(last.begin(), last.end(), 0);
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = 2; j <= x / j; ++j) {
                if (x % j == 0) {
                    if (first.count(j)) {
                        last[first[j]] = i;
                    } else {
                        first[j] = i;
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (first.count(x)) {
                    last[first[x]] = i;
                } else {
                    first[x] = i;
                }
            }
        }
        int mx = last[0];
        for (int i = 0; i < n; ++i) {
            if (mx < i) {
                return mx;
            }
            mx = max(mx, last[i]);
        }
        return -1;
    }
};
```

### **Go**

```go
func findValidSplit(nums []int) int {
	first := map[int]int{}
	n := len(nums)
	last := make([]int, n)
	for i := range last {
		last[i] = i
	}
	for i, x := range nums {
		for j := 2; j <= x/j; j++ {
			if x%j == 0 {
				if k, ok := first[j]; ok {
					last[k] = i
				} else {
					first[j] = i
				}
				for x%j == 0 {
					x /= j
				}
			}
		}
		if x > 1 {
			if k, ok := first[x]; ok {
				last[k] = i
			} else {
				first[x] = i
			}
		}
	}
	mx := last[0]
	for i, x := range last {
		if mx < i {
			return mx
		}
		mx = max(mx, x)
	}
	return -1
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
