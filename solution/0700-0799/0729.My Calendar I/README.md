# [729. 我的日程安排表 I](https://leetcode.cn/problems/my-calendar-i)

[English Version](/solution/0700-0799/0729.My%20Calendar%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现一个 <code>MyCalendar</code> 类来存放你的日程安排。如果要添加的日程安排不会造成 <strong>重复预订</strong> ，则可以存储这个新的日程安排。</p>

<p>当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 <strong>重复预订</strong> 。</p>

<p>日程可以用一对整数 <code>start</code> 和 <code>end</code> 表示，这里的时间是半开区间，即 <code>[start, end)</code>, 实数&nbsp;<code>x</code> 的范围为， &nbsp;<code>start &lt;= x &lt; end</code> 。</p>

<p>实现 <code>MyCalendar</code> 类：</p>

<ul>
	<li><code>MyCalendar()</code> 初始化日历对象。</li>
	<li><code>boolean book(int start, int end)</code> 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 <code>true</code> 。否则，返回 <code>false</code>&nbsp;并且不要将该日程安排添加到日历中。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["MyCalendar", "book", "book", "book"]
[[], [10, 20], [15, 25], [20, 30]]
<strong>输出：</strong>
[null, true, false, true]

<strong>解释：</strong>
MyCalendar myCalendar = new MyCalendar();
myCalendar.book(10, 20); // return True
myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
	<li>每个测试用例，调用 <code>book</code> 方法的次数最多不超过 <code>1000</code> 次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedDict


class MyCalendar:
    def __init__(self):
        self.sd = SortedDict()

    def book(self, start: int, end: int) -> bool:
        idx = self.sd.bisect_right(start)
        if idx < len(self.sd) and end > self.sd.values()[idx]:
            return False
        self.sd[end] = start
        return True


# Your MyCalendar object will be instantiated and called as such:
# obj = MyCalendar()
# param_1 = obj.book(start,end)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
import java.util.Map;
import java.util.TreeMap;

class MyCalendar {

    private final TreeMap<Integer, Integer> tm = new TreeMap<>();

    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> ent = tm.floorEntry(start);
        if (ent != null && ent.getValue() > start) {
            return false;
        }
        ent = tm.ceilingEntry(start);
        if (ent != null && ent.getKey() < end) {
            return false;
        }
        tm.put(start, end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such: MyCalendar
 * obj = new MyCalendar(); boolean param_1 = obj.book(start,end);
 */
```

### **Go**

```go
type MyCalendar struct {
	rbt *redblacktree.Tree
}

func Constructor() MyCalendar {
	return MyCalendar{
		rbt: redblacktree.NewWithIntComparator(),
	}
}

func (this *MyCalendar) Book(start int, end int) bool {
	if p, ok := this.rbt.Floor(start); ok && p.Value.(int) > start {
		return false
	}
	if p, ok := this.rbt.Ceiling(start); ok && p.Key.(int) < end {
		return false
	}
	this.rbt.Put(start, end)
	return true
}


/**
 * Your MyCalendar object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(start,end);
 */
```

### **C++**

```cpp
class MyCalendar {
public:
    map<int, int> m;

    MyCalendar() {
    }

    bool book(int start, int end) {
        ++m[start];
        --m[end];
        int s = 0;
        for (auto& [k, v] : m) {
            s += v;
            if (s > 1) {
                --m[start];
                ++m[end];
                return false;
            }
        }
        return true;
    }
};

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar* obj = new MyCalendar();
 * bool param_1 = obj->book(start,end);
 */
```

### **TypeScript**

```ts
class MyCalendar {
    private calendar: number[][];

    constructor() {
        this.calendar = [];
    }

    book(start: number, end: number): boolean {
        for (const item of this.calendar) {
            if (end <= item[0] || item[1] <= start) {
                continue;
            }
            return false;
        }
        this.calendar.push([start, end]);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * var obj = new MyCalendar()
 * var param_1 = obj.book(start,end)
 */
```

### **Rust**

```rust
use std::collections::BTreeMap;

struct MyCalendar {
    bt: BTreeMap<i32, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyCalendar {
    fn new() -> Self {
        MyCalendar {
            bt: BTreeMap::new(),
        }
    }

    fn book(&mut self, start: i32, end: i32) -> bool {
        if let Some((_, &val)) = self.bt.range(..=start).last() {
            println!("{} {} {}", start, end, val);
            if val > start {
                return false;
            }
        }
        if let Some((&key, _)) = self.bt.range(start..).next() {
            if key < end {
                return false;
            }
        }
        self.bt.insert(start, end);
        true
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * let obj = MyCalendar::new();
 * let ret_1: bool = obj.book(start, end);
 */
```

### **...**

```

```

<!-- tabs:end -->
