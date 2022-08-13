# [2321. 拼接数组的最大分数](https://leetcode.cn/problems/maximum-score-of-spliced-array)

[English Version](/solution/2300-2399/2321.Maximum%20Score%20Of%20Spliced%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的整数数组 <code>nums1</code> 和 <code>nums2</code> ，长度都是 <code>n</code> 。</p>

<p>你可以选择两个整数 <code>left</code> 和 <code>right</code> ，其中 <code>0 &lt;= left &lt;= right &lt; n</code> ，接着 <strong>交换</strong> 两个子数组 <code>nums1[left...right]</code> 和 <code>nums2[left...right]</code> 。</p>

<ul>
	<li>例如，设 <code>nums1 = [1,2,3,4,5]</code> 和 <code>nums2 = [11,12,13,14,15]</code> ，整数选择 <code>left = 1</code> 和 <code>right = 2</code>，那么 <code>nums1</code> 会变为 <code>[1,<strong><em>12</em>,<em>13</em></strong>,4,5]</code> 而 <code>nums2</code> 会变为 <code>[11,<em><strong>2,3</strong></em>,14,15]</code> 。</li>
</ul>

<p>你可以选择执行上述操作 <strong>一次</strong> 或不执行任何操作。</p>

<p>数组的 <strong>分数</strong> 取 <code>sum(nums1)</code> 和 <code>sum(nums2)</code> 中的最大值，其中 <code>sum(arr)</code> 是数组 <code>arr</code> 中所有元素之和。</p>

<p>返回 <strong>可能的最大分数</strong> 。</p>

<p><strong>子数组 </strong>是数组中连续的一个元素序列。<code>arr[left...right]</code> 表示子数组包含 <code>nums</code> 中下标 <code>left</code> 和 <code>right</code> 之间的元素<strong>（含</strong> 下标 <code>left</code> 和 <code>right</code> 对应元素<strong>）</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [60,60,60], nums2 = [10,90,10]
<strong>输出：</strong>210
<strong>解释：</strong>选择 left = 1 和 right = 1 ，得到 nums1 = [60,<em><strong>90</strong></em>,60] 和 nums2 = [10,<em><strong>60</strong></em>,10] 。
分数为 max(sum(nums1), sum(nums2)) = max(210, 80) = 210 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
<strong>输出：</strong>220
<strong>解释：</strong>选择 left = 3 和 right = 4 ，得到 nums1 = [20,40,20,<em><strong>40,20</strong></em>] 和 nums2 = [50,20,50,<em><strong>70,30</strong></em>] 。
分数为 max(sum(nums1), sum(nums2)) = max(140, 220) = 220 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [7,11,13], nums2 = [1,1,1]
<strong>输出：</strong>31
<strong>解释：</strong>选择不交换任何子数组。
分数为 max(sum(nums1), sum(nums2)) = max(31, 3) = 31 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumsSplicedArray(self, nums1: List[int], nums2: List[int]) -> int:
        def f(nums1, nums2):
            d = [a - b for a, b in zip(nums1, nums2)]
            t = mx = d[0]
            for v in d[1:]:
                if t > 0:
                    t += v
                else:
                    t = v
                mx = max(mx, t)
            return mx

        s1, s2 = sum(nums1), sum(nums2)
        return max(s2 + f(nums1, nums2), s1 + f(nums2, nums1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int s1 = 0, s2 = 0, n = nums1.length;
        for (int i = 0; i < n; ++i) {
            s1 += nums1[i];
            s2 += nums2[i];
        }
        return Math.max(s2 + f(nums1, nums2), s1 + f(nums2, nums1));
    }

    private int f(int[] nums1, int[] nums2) {
        int t = nums1[0] - nums2[0];
        int mx = t;
        for (int i = 1; i < nums1.length; ++i) {
            int v = nums1[i] - nums2[i];
            if (t > 0) {
                t += v;
            } else {
                t = v;
            }
            mx = Math.max(mx, t);
        }
        return mx;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumsSplicedArray(vector<int>& nums1, vector<int>& nums2) {
        int s1 = 0, s2 = 0, n = nums1.size();
        for (int i = 0; i < n; ++i) {
            s1 += nums1[i];
            s2 += nums2[i];
        }
        return max(s2 + f(nums1, nums2), s1 + f(nums2, nums1));
    }

    int f(vector<int>& nums1, vector<int>& nums2) {
        int t = nums1[0] - nums2[0];
        int mx = t;
        for (int i = 1; i < nums1.size(); ++i) {
            int v = nums1[i] - nums2[i];
            if (t > 0)
                t += v;
            else
                t = v;
            mx = max(mx, t);
        }
        return mx;
    }
};
```

### **Go**

```go
func maximumsSplicedArray(nums1 []int, nums2 []int) int {
	s1, s2 := 0, 0
	n := len(nums1)
	for i, v := range nums1 {
		s1 += v
		s2 += nums2[i]
	}
	f := func(nums1, nums2 []int) int {
		t := nums1[0] - nums2[0]
		mx := t
		for i := 1; i < n; i++ {
			v := nums1[i] - nums2[i]
			if t > 0 {
				t += v
			} else {
				t = v
			}
			mx = max(mx, t)
		}
		return mx
	}
	return max(s2+f(nums1, nums2), s1+f(nums2, nums1))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
