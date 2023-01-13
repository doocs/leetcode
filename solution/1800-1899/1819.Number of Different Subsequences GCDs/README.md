# [1819. 序列中不同最大公约数的数目](https://leetcode.cn/problems/number-of-different-subsequences-gcds)

[English Version](/solution/1800-1899/1819.Number%20of%20Different%20Subsequences%20GCDs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由正整数组成的数组 <code>nums</code> 。</p>

<p>数字序列的 <strong>最大公约数</strong> 定义为序列中所有整数的共有约数中的最大整数。</p>

<ul>
	<li>例如，序列 <code>[4,6,16]</code> 的最大公约数是 <code>2</code> 。</li>
</ul>

<p>数组的一个 <strong>子序列</strong> 本质是一个序列，可以通过删除数组中的某些元素（或者不删除）得到。</p>

<ul>
	<li>例如，<code>[2,5,10]</code> 是 <code>[1,2,1,<strong>2</strong>,4,1,<strong>5</strong>,<strong>10</strong>]</code> 的一个子序列。</li>
</ul>

<p>计算并返回 <code>nums</code> 的所有 <strong>非空</strong> 子序列中 <strong>不同</strong> 最大公约数的 <strong>数目</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1819.Number%20of%20Different%20Subsequences%20GCDs/images/image-1.png" />
<pre>
<strong>输入：</strong>nums = [6,10,3]
<strong>输出：</strong>5
<strong>解释：</strong>上图显示了所有的非空子序列与各自的最大公约数。
不同的最大公约数为 6 、10 、3 、2 和 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,15,40,5,6]
<strong>输出：</strong>7
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 2 * 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 数学**

对于数组 `nums` 的所有子序列，其最大公约数一定不超过数组中的最大值 $mx$。

因此我们可以枚举 $[1,.. mx]$ 中的每个数 $x$，判断 $x$ 是否为数组 `nums` 的子序列的最大公约数，如果是，则答案加一。

那么问题转换为：判断 $x$ 是否为数组 `nums` 的子序列的最大公约数。我们可以通过枚举 $x$ 的倍数 $y$，判断 $y$ 是否在数组 `nums` 中，如果 $y$ 在数组 `nums` 中，则计算 $y$ 的最大公约数 $g$，如果出现 $g = x$，则 $x$ 是数组 `nums` 的子序列的最大公约数。

时间复杂度 $O(n + M \times \log M)$，空间复杂度 $O(M)$。其中 $n$ 和 $M$ 分别是数组 `nums` 的长度和数组 `nums` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDifferentSubsequenceGCDs(self, nums: List[int]) -> int:
        mx = max(nums)
        vis = set(nums)
        ans = 0
        for x in range(1, mx + 1):
            g = 0
            for y in range(x, mx + 1, x):
                if y in vis:
                    g = gcd(g, y)
                    if g == x:
                        ans += 1
                        break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        boolean[] vis = new boolean[mx + 1];
        for (int x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x = 1; x <= mx; ++x) {
            int g = 0;
            for (int y = x; y <= mx; y += x) {
                if (vis[y]) {
                    g = gcd(g, y);
                    if (x == g) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countDifferentSubsequenceGCDs(vector<int>& nums) {
        int mx = *max_element(nums.begin(), nums.end());
        vector<bool> vis(mx + 1);
        for (int& x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x = 1; x <= mx; ++x) {
            int g = 0;
            for (int y = x; y <= mx; y += x) {
                if (vis[y]) {
                    g = gcd(g, y);
                    if (g == x) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countDifferentSubsequenceGCDs(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		mx = max(mx, x)
	}
	vis := make([]bool, mx+1)
	for _, x := range nums {
		vis[x] = true
	}
	for x := 1; x <= mx; x++ {
		g := 0
		for y := x; y <= mx; y += x {
			if vis[y] {
				g = gcd(g, y)
				if g == x {
					ans++
					break
				}
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
