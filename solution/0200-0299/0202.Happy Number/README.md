# [202. 快乐数](https://leetcode.cn/problems/happy-number)

[English Version](/solution/0200-0299/0202.Happy%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个算法来判断一个数 <code>n</code> 是不是快乐数。</p>

<p><strong>「快乐数」</strong>&nbsp;定义为：</p>

<ul>
	<li>对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。</li>
	<li>然后重复这个过程直到这个数变为 1，也可能是 <strong>无限循环</strong> 但始终变不到 1。</li>
	<li>如果这个过程 <strong>结果为</strong>&nbsp;1，那么这个数就是快乐数。</li>
</ul>

<p>如果 <code>n</code> 是 <em>快乐数</em> 就返回 <code>true</code> ；不是，则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 19
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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

简单模拟，有可能进入死循环导致无法停止，有几种方式解决：

-   哈希表：转换过程不会重复出现同一个数字。
-   限制转换次数：在一定次数转换后还未成功变为 1，那么就断言此数不是快乐数。
-   快慢指针：与判断链表是否存在环原理一致。

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
                s += digit**2
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

### **C++**

```cpp
class Solution {
public:
    bool isHappy(int n) {
        auto getNext = [](int n) {
            int res = 0;
            while (n) {
                res += pow(n % 10, 2);
                n /= 10;
            }
            return res;
        };
        int slow = n;
        int fast = getNext(n);
        while (slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return slow == 1;
    }
};
```

### **TypeScript**

```ts
function isHappy(n: number): boolean {
    const getNext = (n: number) => {
        let res = 0;
        while (n !== 0) {
            res += (n % 10) ** 2;
            n = Math.floor(n / 10);
        }
        return res;
    };

    let slow = n;
    let fast = getNext(n);
    while (slow !== fast) {
        slow = getNext(slow);
        fast = getNext(getNext(fast));
    }
    return fast === 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_happy(n: i32) -> bool {
        let get_next = |mut n: i32| {
            let mut res = 0;
            while n != 0 {
                res += (n % 10).pow(2);
                n /= 10;
            }
            res
        };
        let mut slow = n;
        let mut fast = get_next(n);
        while slow != fast {
            slow = get_next(slow);
            fast = get_next(get_next(fast));
        }
        slow == 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
