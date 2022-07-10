# [2336. Smallest Number in Infinite Set](https://leetcode.com/problems/smallest-number-in-infinite-set)

[中文文档](/solution/2300-2399/2336.Smallest%20Number%20in%20Infinite%20Set/README.md)

## Description

<p>You have a set which contains all positive integers <code>[1, 2, 3, 4, 5, ...]</code>.</p>

<p>Implement the <code>SmallestInfiniteSet</code> class:</p>

<ul>
	<li><code>SmallestInfiniteSet()</code> Initializes the <strong>SmallestInfiniteSet</strong> object to contain <strong>all</strong> positive integers.</li>
	<li><code>int popSmallest()</code> <strong>Removes</strong> and returns the smallest integer contained in the infinite set.</li>
	<li><code>void addBack(int num)</code> <strong>Adds</strong> a positive integer <code>num</code> back into the infinite set, if it is <strong>not</strong> already in the infinite set.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SmallestInfiniteSet&quot;, &quot;addBack&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;, &quot;addBack&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;]
[[], [2], [], [], [], [1], [], [], []]
<strong>Output</strong>
[null, null, 1, 2, 3, null, 1, 4, 5]

<strong>Explanation</strong>
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
	<li>At most <code>1000</code> calls will be made <strong>in total</strong> to <code>popSmallest</code> and <code>addBack</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class SmallestInfiniteSet:

    def __init__(self):
        self.black = set()

    def popSmallest(self) -> int:
        i = 1
        while i in self.black:
            i += 1
        self.black.add(i)
        return i

    def addBack(self, num: int) -> None:
        self.black.discard(num)


# Your SmallestInfiniteSet object will be instantiated and called as such:
# obj = SmallestInfiniteSet()
# param_1 = obj.popSmallest()
# obj.addBack(num)
```

### **Java**

```java
class SmallestInfiniteSet {
    private Set<Integer> black = new HashSet<>();

    public SmallestInfiniteSet() {

    }
    
    public int popSmallest() {
        int i = 1;
        for (; black.contains(i); ++i);
        black.add(i);
        return i;
    }
    
    public void addBack(int num) {
        black.remove(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
```

### **C++**

```cpp
class SmallestInfiniteSet {
public:
    unordered_set<int> black;

    SmallestInfiniteSet() {

    }
    
    int popSmallest() {
        int i = 1;
        for (; black.count(i); ++i);
        black.insert(i);
        return i;
    }
    
    void addBack(int num) {
        black.erase(num);
    }
};

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet* obj = new SmallestInfiniteSet();
 * int param_1 = obj->popSmallest();
 * obj->addBack(num);
 */
```

### **Go**

```go
type SmallestInfiniteSet struct {
	black map[int]bool
}

func Constructor() SmallestInfiniteSet {
	s := map[int]bool{}
	return SmallestInfiniteSet{s}
}

func (this *SmallestInfiniteSet) PopSmallest() int {
	i := 1
	for ; this.black[i]; i++ {
	}
	this.black[i] = true
	return i
}

func (this *SmallestInfiniteSet) AddBack(num int) {
	this.black[num] = false
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.PopSmallest();
 * obj.AddBack(num);
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
