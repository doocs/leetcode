# [170. Two Sum III - Data structure design](https://leetcode.com/problems/two-sum-iii-data-structure-design)

[中文文档](/solution/0100-0199/0170.Two%20Sum%20III%20-%20Data%20structure%20design/README.md)

## Description

<p>Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.</p>

<p>Implement the <code>TwoSum</code> class:</p>

<ul>
	<li><code>TwoSum()</code> Initializes the <code>TwoSum</code> object, with an empty array initially.</li>
	<li><code>void add(int number)</code> Adds <code>number</code> to the data structure.</li>
	<li><code>boolean find(int value)</code> Returns <code>true</code> if there exists any pair of numbers whose sum is equal to <code>value</code>, otherwise, it returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TwoSum&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;find&quot;, &quot;find&quot;]
[[], [1], [3], [5], [4], [7]]
<strong>Output</strong>
[null, null, null, null, true, false]

<strong>Explanation</strong>
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --&gt; [1]
twoSum.add(3);   // [1] --&gt; [1,3]
twoSum.add(5);   // [1,3] --&gt; [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>5</sup> &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= value &lt;= 2<sup>31</sup> - 1</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>add</code> and <code>find</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
