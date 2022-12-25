# [2513. 最小化两个数组中的最大值](https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays)

[English Version](/solution/2500-2599/2513.Minimize%20the%20Maximum%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个数组&nbsp;<code>arr1</code> 和&nbsp;<code>arr2</code>&nbsp;，它们一开始都是空的。你需要往它们中添加正整数，使它们满足以下条件：</p>

<ul>
	<li><code>arr1</code>&nbsp;包含&nbsp;<code>uniqueCnt1</code>&nbsp;个<strong>&nbsp;互不相同</strong>&nbsp;的正整数，每个整数都&nbsp;<strong>不能 </strong>被&nbsp;<code>divisor1</code>&nbsp;<strong>整除</strong>&nbsp;。</li>
	<li><code>arr2</code>&nbsp;包含&nbsp;<code>uniqueCnt2</code>&nbsp;个<strong>&nbsp;互不相同</strong>&nbsp;的正整数，每个整数都&nbsp;<strong>不能</strong>&nbsp;被&nbsp;<code>divisor2</code>&nbsp;<strong>整除</strong>&nbsp;。</li>
	<li><code>arr1</code> 和&nbsp;<code>arr2</code>&nbsp;中的元素&nbsp;<strong>互不相同</strong>&nbsp;。</li>
</ul>

<p>给你&nbsp;<code>divisor1</code>&nbsp;，<code>divisor2</code>&nbsp;，<code>uniqueCnt1</code>&nbsp;和&nbsp;<code>uniqueCnt2</code>&nbsp;，请你返回两个数组中&nbsp;<strong>最大元素</strong>&nbsp;的&nbsp;<strong>最小值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
<b>输出：</b>4
<b>解释：</b>
我们可以把前 4 个自然数划分到 arr1 和 arr2 中。
arr1 = [1] 和 arr2 = [2,3,4] 。
可以看出两个数组都满足条件。
最大值是 4 ，所以返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
<b>输出：</b>3
<b>解释：</b>
arr1 = [1,2] 和 arr2 = [3] 满足所有条件。
最大值是 3 ，所以返回 3 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
<b>输出：</b>15
<b>解释：</b>
最终数组为 arr1 = [1,3,5,7,9,11,13,15] 和 arr2 = [2,6] 。
上述方案是满足所有条件的最优解。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= divisor1, divisor2 &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= uniqueCnt1, uniqueCnt2 &lt; 10<sup>9</sup></code></li>
	<li><code>2 &lt;= uniqueCnt1 + uniqueCnt2 &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimizeSet(
        self, divisor1: int, divisor2: int, uniqueCnt1: int, uniqueCnt2: int
    ) -> int:
        def f(x):
            cnt1 = x // divisor1 * (divisor1 - 1) + x % divisor1
            cnt2 = x // divisor2 * (divisor2 - 1) + x % divisor2
            cnt = x // divisor * (divisor - 1) + x % divisor
            return (
                cnt1 >= uniqueCnt1
                and cnt2 >= uniqueCnt2
                and cnt >= uniqueCnt1 + uniqueCnt2
            )

        divisor = lcm(divisor1, divisor2)
        return bisect_left(range(10**10), True, key=f)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long divisor = lcm(divisor1, divisor2);
        long left = 1, right = 10000000000L;
        while (left < right) {
            long mid = (left + right) >> 1;
            long cnt1 = mid / divisor1 * (divisor1 - 1) + mid % divisor1;
            long cnt2 = mid / divisor2 * (divisor2 - 1) + mid % divisor2;
            long cnt = mid / divisor * (divisor - 1) + mid % divisor;
            if (cnt1 >= uniqueCnt1 && cnt2 >= uniqueCnt2 && cnt >= uniqueCnt1 + uniqueCnt2) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    private long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
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
    int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long left = 1, right = 1e10;
        long divisor = lcm((long) divisor1, (long) divisor2);
        while (left < right) {
            long mid = (left + right) >> 1;
            long cnt1 = mid / divisor1 * (divisor1 - 1) + mid % divisor1;
            long cnt2 = mid / divisor2 * (divisor2 - 1) + mid % divisor2;
            long cnt = mid / divisor * (divisor - 1) + mid % divisor;
            if (cnt1 >= uniqueCnt1 && cnt2 >= uniqueCnt2 && cnt >= uniqueCnt1 + uniqueCnt2) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func minimizeSet(divisor1 int, divisor2 int, uniqueCnt1 int, uniqueCnt2 int) int {
	divisor := lcm(divisor1, divisor2)
	left, right := 1, 10000000000
	for left < right {
		mid := (left + right) >> 1
		cnt1 := mid/divisor1*(divisor1-1) + mid%divisor1
		cnt2 := mid/divisor2*(divisor2-1) + mid%divisor2
		cnt := mid/divisor*(divisor-1) + mid%divisor
		if cnt1 >= uniqueCnt1 && cnt2 >= uniqueCnt2 && cnt >= uniqueCnt1+uniqueCnt2 {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func lcm(a, b int) int {
	return a * b / gcd(a, b)
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
