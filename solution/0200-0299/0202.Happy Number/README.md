# [202. 快乐数](https://leetcode-cn.com/problems/happy-number)

[English Version](/solution/0200-0299/0202.Happy%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个算法来判断一个数 <code>n</code> 是不是快乐数。</p>

<p>「快乐数」定义为：</p>

<ul>
	<li>对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。</li>
	<li>然后重复这个过程直到这个数变为 1，也可能是 <strong>无限循环</strong> 但始终变不到 1。</li>
	<li>如果 <strong>可以变为</strong>  1，那么这个数就是快乐数。</li>
</ul>

<p>如果 <code>n</code> 是快乐数就返回 <code>true</code> ；不是，则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>19
<strong>输出：</strong>true
<strong>解释：
</strong>1<sup>2</sup> + 9<sup>2</sup> = 82
8<sup>2</sup> + 2<sup>2</sup> = 68
6<sup>2</sup> + 8<sup>2</sup> = 100
1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isHappy(self, n: int) -> bool:
        def get_next(n):
            s = 0
            while n > 0:
                n, digit = divmod(n, 10)
                s += digit ** 2
            return s

        visited = set()
        while n != 1 and n not in visited:
            visited.add(n)
            n = get_next(n)
        return n == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();
        while (n != 1 && !visited.contains(n)) {
            visited.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int s = 0;
        while (n > 0) {
            int digit = n % 10;
            s += digit * digit;
            n /= 10;
        }
        return s;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
