# [729. My Calendar I](https://leetcode.com/problems/my-calendar-i)

[中文文档](/solution/0700-0799/0729.My%20Calendar%20I/README.md)

## Description

<p>You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a <strong>double booking</strong>.</p>

<p>A <strong>double booking</strong> happens when two events have some non-empty intersection (i.e., some moment is common to both events.).</p>

<p>The event can be represented as a pair of integers <code>start</code> and <code>end</code> that represents a booking on the half-open interval <code>[start, end)</code>, the range of real numbers <code>x</code> such that <code>start &lt;= x &lt; end</code>.</p>

<p>Implement the <code>MyCalendar</code> class:</p>

<ul>
	<li><code>MyCalendar()</code> Initializes the calendar object.</li>
	<li><code>boolean book(int start, int end)</code> Returns <code>true</code> if the event can be added to the calendar successfully without causing a <strong>double booking</strong>. Otherwise, return <code>false</code> and do not add the event to the calendar.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MyCalendar&quot;, &quot;book&quot;, &quot;book&quot;, &quot;book&quot;]
[[], [10, 20], [15, 25], [20, 30]]
<strong>Output</strong>
[null, true, false, true]

<strong>Explanation</strong>
MyCalendar myCalendar = new MyCalendar();
myCalendar.book(10, 20); // return True
myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>1000</code> calls will be made to <code>book</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
