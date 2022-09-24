# [788. 旋转数字](https://leetcode.cn/problems/rotated-digits)

[English Version](/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。</p>

<p>如果一个数的每位数字被旋转以后仍然还是一个数字，&nbsp;则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。</p>

<p>现在我们有一个正整数&nbsp;<code>N</code>, 计算从&nbsp;<code>1</code> 到&nbsp;<code>N</code> 中有多少个数&nbsp;X 是好数？</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> 10
<strong>输出:</strong> 4
<strong>解释:</strong> 
在[1, 10]中有四个好数： 2, 5, 6, 9。
注意 1 和 10 不是好数, 因为他们在旋转之后不变。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>N&nbsp;的取值范围是&nbsp;<code>[1, 10000]</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接枚举**

一种直观且有效的思路是，直接枚举 $[1, n]$ 中的每个数，判断其是否为好数，若为好数，则答案加一。

那么题目的重点转化为如何判断一个数字 $x$ 是否为好数。判断的逻辑如下：

我们先用哈希表 $d$ 记录每个有效数字对应的旋转数字，在这道题中，有效数字有 `0, 1, 8, 2, 5, 6, 9`，分别对应旋转数字 `0, 1, 8, 5, 2, 9, 6`。

然后遍历数字 $x$ 的每一位数字 $v$，如果 $v$ 不在哈希表 $d$ 中，则说明 $x$ 不是好数，直接返回 `false`。否则，将数字 $v$ 对应的旋转数字 $d[v]$ 加入到 $y$ 中。最后，判断 $x$ 和 $y$ 是否相等，若不相等，则说明 $x$ 是好数，返回 `true`。

时间复杂度 $O(n\times \log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        def check(x):
            y, t = 0, x
            k = 1
            while t:
                v = t % 10
                if v not in d:
                    return False
                y = d[v] * k + y
                k *= 10
                t //= 10
            return x != y

        d = {0: 0, 1: 1, 8: 8, 2: 5, 5: 2, 6: 9, 9: 6}
        return sum(check(i) for i in range(1, n + 1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final Map<Integer, Integer> d = new HashMap<>();
    static {
        d.put(0, 0);
        d.put(1, 1);
        d.put(8, 8);
        d.put(2, 5);
        d.put(5, 2);
        d.put(6, 9);
        d.put(9, 6);
    }

    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (check(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t > 0) {
            int v = t % 10;
            if (!d.containsKey(v)) {
                return false;
            }
            y = d.get(v) * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, int> d{{0, 0}, {1, 1}, {8, 8}, {2, 5}, {5, 2}, {6, 9}, {9, 6}};

    int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }

    bool check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t) {
            int v = t % 10;
            if (!d.count(v)) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
};
```

### **Go**

```go
func rotatedDigits(n int) int {
	d := map[int]int{0: 0, 1: 1, 8: 8, 2: 5, 5: 2, 6: 9, 9: 6}
	check := func(x int) bool {
		y, t := 0, x
		k := 1
		for ; t > 0; t /= 10 {
			v := t % 10
			if _, ok := d[v]; !ok {
				return false
			}
			y = d[v]*k + y
			k *= 10
		}
		return x != y
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
