---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20058.%20%E6%97%A5%E7%A8%8B%E8%A1%A8/README.md
---

<!-- problem:start -->

# [剑指 Offer II 058. 日程表](https://leetcode.cn/problems/fi9suh)

## 题目描述

<!-- description:start -->

<p>请实现一个 <code>MyCalendar</code> 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。</p>

<p><code>MyCalendar</code> 有一个 <code>book(int start, int end)</code>方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 <code>[start, end)</code>, 实数&nbsp;<code>x</code> 的范围为， &nbsp;<code>start &lt;= x &lt; end</code>。</p>

<p>当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。</p>

<p>每次调用 <code>MyCalendar.book</code>方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 <code>true</code>。否则，返回 <code>false</code>&nbsp;并且不要将该日程安排添加到日历中。</p>

<p>请按照以下步骤调用 <code>MyCalendar</code> 类: <code>MyCalendar cal = new MyCalendar();</code> <code>MyCalendar.book(start, end)</code></p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:
</strong>[&quot;MyCalendar&quot;,&quot;book&quot;,&quot;book&quot;,&quot;book&quot;]
[[],[10,20],[15,25],[20,30]]
<strong>输出:</strong> [null,true,false,true]
<strong>解释:</strong>
MyCalendar myCalendar = new MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false ，第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了
MyCalendar.book(20, 30); // returns true ，第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>每个测试用例，调用&nbsp;<code>MyCalendar.book</code>&nbsp;函数最多不超过&nbsp;<code>1000</code>次。</li>
	<li><code>0 &lt;= start &lt; end &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 729&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/my-calendar-i/">https://leetcode.cn/problems/my-calendar-i/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class MyCalendar:
    def __init__(self):
        self.sd = SortedDict()

    def book(self, start: int, end: int) -> bool:
        idx = self.sd.bisect_right(start)
        if 0 <= idx < len(self.sd):
            if end > self.sd.values()[idx]:
                return False
        self.sd[end] = start
        return True


# Your MyCalendar object will be instantiated and called as such:
# obj = MyCalendar()
# param_1 = obj.book(start,end)
```

#### Java

```java
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

#### Go

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

#### Swift

```swift
class MyCalendar {

    private var calendar: [(Int, Int)]

    init() {
        self.calendar = []
    }

    func book(_ start: Int, _ end: Int) -> Bool {
        let newEvent = (start, end)
        let index = calendar.firstIndex { $0.0 >= newEvent.1 } ?? calendar.count

        if index > 0 && calendar[index - 1].1 > newEvent.0 {
            return false
        }

        calendar.insert(newEvent, at: index)
        return true
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * let obj = MyCalendar()
 * let ret = obj.book(start, end)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
