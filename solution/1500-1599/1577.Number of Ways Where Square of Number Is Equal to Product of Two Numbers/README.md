# [1577. 数的平方等于两数乘积的方法数](https://leetcode.cn/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers)

[English Version](/solution/1500-1599/1577.Number%20of%20Ways%20Where%20Square%20of%20Number%20Is%20Equal%20to%20Product%20of%20Two%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code> ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：</p>

<ul>
	<li>类型 1：三元组 <code>(i, j, k)</code> ，如果 <code>nums1[i]<sup>2</sup>&nbsp;== nums2[j] * nums2[k]</code> 其中 <code>0 &lt;= i &lt; nums1.length</code> 且 <code>0 &lt;= j &lt; k &lt; nums2.length</code></li>
	<li>类型 2：三元组 <code>(i, j, k)</code> ，如果 <code>nums2[i]<sup>2</sup>&nbsp;== nums1[j] * nums1[k]</code> 其中 <code>0 &lt;= i &lt; nums2.length</code> 且 <code>0 &lt;= j &lt; k &lt; nums1.length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [7,4], nums2 = [5,2,8,9]
<strong>输出：</strong>1
<strong>解释：</strong>类型 1：(1,1,2), nums1[1]^2 = nums2[1] * nums2[2] (4^2 = 2 * 8)</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums1 = [1,1], nums2 = [1,1,1]
<strong>输出：</strong>9
<strong>解释：</strong>所有三元组都符合题目要求，因为 1^2 = 1 * 1
类型 1：(0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2), nums1[i]^2 = nums2[j] * nums2[k]
类型 2：(0,0,1), (1,0,1), (2,0,1), nums2[i]^2 = nums1[j] * nums1[k]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums1 = [7,7,8,3], nums2 = [1,2,9,7]
<strong>输出：</strong>2
<strong>解释：</strong>有两个符合题目要求的三元组
类型 1：(3,0,2), nums1[3]^2 = nums2[0] * nums2[2]
类型 2：(3,0,1), nums2[3]^2 = nums1[0] * nums1[1]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
<strong>输出：</strong>0
<strong>解释：</strong>不存在符合题目要求的三元组
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们用哈希表 `cnt1` 统计 `nums1` 中每个数出现的次数，用哈希表 `cnt2` 统计 `nums2` 中每个数出现的次数。

然后我们双重循环遍历两个哈希表，记当前 `cnt1` 遍历到的键值对为 $(a, x)$，当前 `cnt2` 遍历到的键值对为 $(b, y)$。接下来分情况讨论：

-   如果 $a^2$ 能被 $b$ 整除，设 $c=\frac{a^2}{b}$，若 $b=c$，那么答案加上 $x \times y \times (y - 1)$，否则答案加上 $x \times y \times cnt2[c]$。
-   如果 $b^2$ 能被 $a$ 整除，设 $c=\frac{b^2}{a}$，若 $a=c$，那么答案加上 $x \times (x - 1) \times y$，否则答案加上 $x \times cnt1[c] \times y$。

最后将答案除以 $2$ 返回即可。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为数组 `nums1` 和 `nums2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        cnt1 = Counter(nums1)
        cnt2 = Counter(nums2)
        ans = 0
        for a, x in cnt1.items():
            for b, y in cnt2.items():
                if a * a % b == 0:
                    c = a * a // b
                    if b == c:
                        ans += x * y * (y - 1)
                    else:
                        ans += x * y * cnt2[c]
                if b * b % a == 0:
                    c = b * b // a
                    if a == c:
                        ans += x * (x - 1) * y
                    else:
                        ans += x * y * cnt1[c]
        return ans >> 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int v : nums1) {
            cnt1.put(v, cnt1.getOrDefault(v, 0) + 1);
        }
        for (int v : nums2) {
            cnt2.put(v, cnt2.getOrDefault(v, 0) + 1);
        }
        long ans = 0;
        for (var e1 : cnt1.entrySet()) {
            long a = e1.getKey(), x = e1.getValue();
            for (var e2 : cnt2.entrySet()) {
                long b = e2.getKey(), y = e2.getValue();
                if ((a * a) % b == 0) {
                    long c = a * a / b;
                    if (b == c) {
                        ans += x * y * (y - 1);
                    } else {
                        ans += x * y * cnt2.getOrDefault((int) c, 0);
                    }
                }
                if ((b * b) % a == 0) {
                    long c = b * b / a;
                    if (a == c) {
                        ans += x * (x - 1) * y;
                    } else {
                        ans += x * y * cnt1.getOrDefault((int) c, 0);
                    }
                }
            }
        }
        return (int) (ans >> 1);
    }
}
```

### **Go**

```go
func numTriplets(nums1 []int, nums2 []int) (ans int) {
	cnt1 := map[int]int{}
	cnt2 := map[int]int{}
	for _, v := range nums1 {
		cnt1[v]++
	}
	for _, v := range nums2 {
		cnt2[v]++
	}
	for a, x := range cnt1 {
		for b, y := range cnt2 {
			if a*a%b == 0 {
				c := a * a / b
				if b == c {
					ans += x * y * (y - 1)
				} else {
					ans += x * y * cnt2[c]
				}
			}
			if b*b%a == 0 {
				c := b * b / a
				if a == c {
					ans += x * (x - 1) * y
				} else {
					ans += x * y * cnt1[c]
				}
			}
		}
	}
	ans /= 2
	return
}
```

### **...**

```

```

<!-- tabs:end -->
