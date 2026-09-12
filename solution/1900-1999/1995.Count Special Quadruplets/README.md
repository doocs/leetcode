---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1995.Count%20Special%20Quadruplets/README.md
rating: 1352
source: 第 257 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 枚举
---

<!-- problem:start -->

# [1995. 统计特殊四元组](https://leetcode.cn/problems/count-special-quadruplets)

[English Version](/solution/1900-1999/1995.Count%20Special%20Quadruplets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>nums</code> ，返回满足下述条件的 <strong>不同</strong> 四元组 <code>(a, b, c, d)</code> 的 <strong>数目</strong> ：</p>

<ul>
	<li><code>nums[a] + nums[b] + nums[c] == nums[d]</code> ，且</li>
	<li><code>a &lt; b &lt; c &lt; d</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,6]
<strong>输出：</strong>1
<strong>解释：</strong>满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [3,3,6,4,5]
<strong>输出：</strong>0
<strong>解释：</strong>[3,3,6,4,5] 中不存在满足要求的四元组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,3,5]
<strong>输出：</strong>4
<strong>解释：</strong>满足要求的 4 个四元组如下：
- (0, 1, 2, 3): 1 + 1 + 1 == 3
- (0, 1, 3, 4): 1 + 1 + 3 == 5
- (0, 2, 3, 4): 1 + 1 + 3 == 5
- (1, 2, 3, 4): 1 + 1 + 3 == 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：暴力枚举

<!-- thinking:start -->

> **思考**
>
> $n\le 50$，四重循环枚举 $a<b<c<d$ 并判断和式，时间可接受。

<!-- thinking:end -->

四重循环枚举 $a < b < c < d$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for a in range(n - 3):
            for b in range(a + 1, n - 2):
                for c in range(b + 1, n - 1):
                    for d in range(c + 1, n):
                        if nums[a] + nums[b] + nums[c] == nums[d]:
                            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int ans = 0, n = nums.length;
        for (int a = 0; a < n - 3; ++a) {
            for (int b = a + 1; b < n - 2; ++b) {
                for (int c = b + 1; c < n - 1; ++c) {
                    for (int d = c + 1; d < n; ++d) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            ++ans;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countQuadruplets(vector<int>& nums) {
        int ans = 0, n = nums.size();
        for (int a = 0; a < n - 3; ++a)
            for (int b = a + 1; b < n - 2; ++b)
                for (int c = b + 1; c < n - 1; ++c)
                    for (int d = c + 1; d < n; ++d)
                        if (nums[a] + nums[b] + nums[c] == nums[d]) ++ans;
        return ans;
    }
};
```

#### Go

```go
func countQuadruplets(nums []int) int {
	ans, n := 0, len(nums)
	for a := 0; a < n-3; a++ {
		for b := a + 1; b < n-2; b++ {
			for c := b + 1; c < n-1; c++ {
				for d := c + 1; d < n; d++ {
					if nums[a]+nums[b]+nums[c] == nums[d] {
						ans++
					}
				}
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表

<!-- thinking:start -->

> **思考**
>
> 四重循环常数偏大。从右固定 $c$，哈希统计其右侧 $d$ 的值，再枚举 $a,b$ 查询 $nums[a]+nums[b]+nums[c]$ 的出现次数，降为 $O(n^3)$。

<!-- thinking:end -->

从右往左枚举 $c$，用哈希表统计右侧 $d$ 的取值，再枚举 $a,b$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        counter = Counter()
        for c in range(n - 2, 1, -1):
            counter[nums[c + 1]] += 1
            for a in range(c - 1):
                for b in range(a + 1, c):
                    ans += counter[nums[a] + nums[b] + nums[c]]
        return ans
```

#### Java

```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int ans = 0, n = nums.length;
        int[] counter = new int[310];
        for (int c = n - 2; c > 1; --c) {
            ++counter[nums[c + 1]];
            for (int a = 0; a < c - 1; ++a) {
                for (int b = a + 1; b < c; ++b) {
                    ans += counter[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countQuadruplets(vector<int>& nums) {
        int ans = 0, n = nums.size();
        vector<int> counter(310);
        for (int c = n - 2; c > 1; --c) {
            ++counter[nums[c + 1]];
            for (int a = 0; a < c - 1; ++a) {
                for (int b = a + 1; b < c; ++b) {
                    ans += counter[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countQuadruplets(nums []int) int {
	ans, n := 0, len(nums)
	counter := make([]int, 310)
	for c := n - 2; c > 1; c-- {
		counter[nums[c+1]]++
		for a := 0; a < c-1; a++ {
			for b := a + 1; b < c; b++ {
				ans += counter[nums[a]+nums[b]+nums[c]]
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：哈希表（枚举优化）

<!-- thinking:start -->

> **思考**
>
> 仍可再降一重：从右枚举 $b$，把所有 $d-c$（$c=b+1$）计入哈希，再枚举 $a$ 查询 $nums[a]+nums[b]$，总时间 $O(n^2)$。

<!-- thinking:end -->

枚举 $b$，维护 $nums[d]-nums[c]$ 的出现次数，将复杂度再降一维。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        counter = Counter()
        for b in range(n - 3, 0, -1):
            c = b + 1
            for d in range(c + 1, n):
                counter[nums[d] - nums[c]] += 1
            for a in range(b):
                ans += counter[nums[a] + nums[b]]
        return ans
```

#### Java

```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int ans = 0, n = nums.length;
        int[] counter = new int[310];
        for (int b = n - 3; b > 0; --b) {
            int c = b + 1;
            for (int d = c + 1; d < n; ++d) {
                if (nums[d] - nums[c] >= 0) {
                    ++counter[nums[d] - nums[c]];
                }
            }
            for (int a = 0; a < b; ++a) {
                ans += counter[nums[a] + nums[b]];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countQuadruplets(vector<int>& nums) {
        int ans = 0, n = nums.size();
        vector<int> counter(310);
        for (int b = n - 3; b > 0; --b) {
            int c = b + 1;
            for (int d = c + 1; d < n; ++d) {
                if (nums[d] - nums[c] >= 0) {
                    ++counter[nums[d] - nums[c]];
                }
            }
            for (int a = 0; a < b; ++a) {
                ans += counter[nums[a] + nums[b]];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countQuadruplets(nums []int) int {
	ans, n := 0, len(nums)
	counter := make([]int, 310)
	for b := n - 3; b > 0; b-- {
		c := b + 1
		for d := c + 1; d < n; d++ {
			if nums[d] >= nums[c] {
				counter[nums[d]-nums[c]]++
			}
		}
		for a := 0; a < b; a++ {
			ans += counter[nums[a]+nums[b]]
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
