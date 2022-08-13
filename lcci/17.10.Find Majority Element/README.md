# [面试题 17.10. 主要元素](https://leetcode.cn/problems/find-majority-element-lcci)

[English Version](/lcci/17.10.Find%20Majority%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>如果数组中多一半的数都是同一个，则称之为主要元素。给定一个<strong>整数</strong>数组，找到它的主要元素。若没有，返回-1。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[1,2,5,9,5,9,5,5,5]
<strong>输出：</strong>5</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[3,2]
<strong>输出：</strong>-1</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[2,2,1,1,1,2,2]
<strong>输出：</strong>2</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong><br>
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：摩尔投票法**

摩尔投票法的基本步骤如下：

初始化元素 $m$，并给计数器 $cnt$ 赋初值 $cnt=0$。对于输入列表中每一个元素 $x$：

1. 若 $cnt=0$，那么 $m=x$ and $cnt=1$；
1. 否则若 $m=x$，那么 $cnt=cnt+1$；
1. 否则 $cnt=cnt-1$。

一般而言，摩尔投票法需要对输入的列表进行**两次遍历**。在第一次遍历中，我们生成候选值 $m$，如果存在多数，那么该候选值就是多数值。在第二次遍历中，只需要简单地计算候选值的频率，以确认是否是多数值。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = m = 0
        for v in nums:
            if cnt == 0:
                m, cnt = v, 1
            else:
                cnt += 1 if m == v else -1
        return m if nums.count(m) > len(nums) // 2 else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else {
                cnt += (m == v ? 1 : -1);
            }
        }
        cnt = 0;
        for (int v : nums) {
            if (m == v) {
                ++cnt;
            }
        }
        return cnt > nums.length / 2 ? m : -1;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0,
        m = 0;
    for (const v of nums) {
        if (cnt == 0) {
            m = v;
            cnt = 1;
        } else {
            cnt += m == v ? 1 : -1;
        }
    }
    cnt = 0;
    for (const v of nums) {
        if (m == v) {
            ++cnt;
        }
    }
    return cnt > nums.length / 2 ? m : -1;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, m = 0;
        for (int& v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else
                cnt += (m == v ? 1 : -1);
        }
        cnt = count(nums.begin(), nums.end(), m);
        return cnt > nums.size() / 2 ? m : -1;
    }
};
```

### **Go**

```go
func majorityElement(nums []int) int {
	cnt, m := 0, 0
	for _, v := range nums {
		if cnt == 0 {
			m, cnt = v, 1
		} else {
			if m == v {
				cnt++
			} else {
				cnt--
			}
		}
	}
	cnt = 0
	for _, v := range nums {
		if m == v {
			cnt++
		}
	}
	if cnt > len(nums)/2 {
		return m
	}
	return -1
}
```

### **C#**

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, m = 0;
        foreach (int v in nums)
        {
            if (cnt == 0)
            {
                m = v;
                cnt = 1;
            }
            else
            {
                cnt += m == v ? 1 : -1;
            }
        }
        cnt = 0;
        foreach (int v in nums)
        {
            if (m == v)
            {
                ++cnt;
            }
        }
        return cnt > nums.Length / 2 ? m : -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
