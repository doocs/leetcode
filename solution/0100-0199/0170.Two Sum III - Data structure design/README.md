# [170. 两数之和 III - 数据结构设计](https://leetcode.cn/problems/two-sum-iii-data-structure-design)

[English Version](/solution/0100-0199/0170.Two%20Sum%20III%20-%20Data%20structure%20design/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。</p>

<p>实现 <code>TwoSum</code> 类：</p>

<ul>
	<li><code>TwoSum()</code> 使用空数组初始化 <code>TwoSum</code> 对象</li>
	<li><code>void add(int number)</code> 向数据结构添加一个数 <code>number</code></li>
	<li><code>boolean find(int value)</code> 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
<strong>输出：</strong>
[null, null, null, null, true, false]

<strong>解释：</strong>
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --&gt; [1]
twoSum.add(3);   // [1] --&gt; [1,3]
twoSum.add(5);   // [1,3] --&gt; [1,3,5]
twoSum.find(4);  // 1 + 3 = 4，返回 true
twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>5</sup> &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= value &lt;= 2<sup>31</sup> - 1</code></li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>add</code> 和 <code>find</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“计数器”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class TwoSum:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.counter = Counter()

    def add(self, number: int) -> None:
        """
        Add the number to an internal data structure..
        """
        self.counter[number] += 1

    def find(self, value: int) -> bool:
        """
        Find if there exists any pair of numbers which sum is equal to the value.
        """
        for num in self.counter.keys():
            other = value - num
            if other in self.counter:
                if other != num:
                    return True
                if other == num and self.counter[num] > 1:
                    return True
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class TwoSum {
    private Map<Integer, Integer> counter;

    /** Initialize your data structure here. */
    public TwoSum() {
        counter = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        counter.put(number, counter.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num : counter.keySet()) {
            int other = value - num;
            if (counter.containsKey(other)) {
                if (num != other) {
                    return true;
                }
                if (num == other && counter.get(other) > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```

### **...**

```

```

<!-- tabs:end -->
