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

**方法一：哈希表**

我们用哈希表 `cnt` 存储数字出现的次数。

调用 `add` 方法时，将数字 `number` 的出现次数加一。

调用 `find` 方法时，遍历哈希表 `cnt`，对于每个键 `x`，判断 `value - x` 是否也是哈希表 `cnt` 的键，如果是，判断 `x` 是否等于 `value - x`，如果不等，说明找到了一对和为 `value` 的数字，返回 `true`；如果等，判断 `x` 的出现次数是否大于 `1`，如果大于 `1`，说明找到了一对和为 `value` 的数字，返回 `true`；如果小于等于 `1`，说明没有找到一对和为 `value` 的数字，继续遍历哈希表 `cnt`，如果遍历结束都没有找到，返回 `false`。

时间复杂度：

-   `add` 方法的时间复杂度为 $O(1)$；
-   `find` 方法的时间复杂度为 $O(n)$。

空间复杂度 $O(n)$，其中 $n$ 为哈希表 `cnt` 的大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class TwoSum:
    def __init__(self):
        self.cnt = Counter()

    def add(self, number: int) -> None:
        self.cnt[number] += 1

    def find(self, value: int) -> bool:
        for x, v in self.cnt.items():
            y = value - x
            if y in self.cnt:
                if x != y or v > 1:
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
    private Map<Integer, Integer> cnt = new HashMap<>();

    public TwoSum() {
    }

    public void add(int number) {
        cnt.merge(number, 1, Integer::sum);
    }

    public boolean find(int value) {
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int y = value - x;
            if (cnt.containsKey(y)) {
                if (x != y || v > 1) {
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

### **C++**

```cpp
class TwoSum {
public:
    TwoSum() {

    }

    void add(int number) {
        ++cnt[number];
    }

    bool find(int value) {
        for (auto& [x, v] : cnt) {
            long y = (long) value - x;
            if (cnt.count(y)) {
                if (x != y || v > 1) {
                    return true;
                }
            }
        }
        return false;
    }

private:
    unordered_map<int, int> cnt;
};

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum* obj = new TwoSum();
 * obj->add(number);
 * bool param_2 = obj->find(value);
 */
```

### **Go**

```go
type TwoSum struct {
	cnt map[int]int
}

func Constructor() TwoSum {
	return TwoSum{map[int]int{}}
}

func (this *TwoSum) Add(number int) {
	this.cnt[number]++
}

func (this *TwoSum) Find(value int) bool {
	for x, v := range this.cnt {
		y := value - x
		if _, ok := this.cnt[y]; ok && (x != y || v > 1) {
			return true
		}
	}
	return false
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(number);
 * param_2 := obj.Find(value);
 */
```

### **...**

```

```

<!-- tabs:end -->
